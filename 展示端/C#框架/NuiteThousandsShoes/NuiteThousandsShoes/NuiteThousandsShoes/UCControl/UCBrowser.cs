using CefSharp;
using CefSharp.WinForms;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.IO.Compression;
using System.Net;
using System.Text;
using System.Timers;
using System.Windows.Forms;

namespace NuiteThousandsShoes
{
    public partial class UCBrowser : UserControl
    {
        private BusDisplayClient busDisplayClient = new BusDisplayClient();
        private static string UCBrowserURL = "";//"http://127.0.0.1:8080/show-admin/shop-data/";
        private static string CacheTopTxt = Application.StartupPath + @"\Caches\ShopTop.txt";
        private static string CacheSettingTxt = Application.StartupPath + @"\Caches\ShopSetting.txt";

        //需要播放的视频table
        private DataTable dtVideos = null;
        public DataTable DtVideos
        {
            get { return dtVideos; }
            set { dtVideos = value; }
        }

        //需要播放的mp3文件table
        private DataTable dtMp3s = null;
        public DataTable DtMp3s
        {
            get { return dtMp3s; }
            set { dtMp3s = value; }
        }

        //正在试穿的鞋子列表   数组为空的时候播放视频
        //private List<TryShoes> tryShoesList = null;
        //public List<TryShoes> TryShoesList
        //{
        //    get { return tryShoesList; }
        //    set { tryShoesList = value; }
        //}

        //当前鞋子队列(用于播放展示)
        private PlayList fPlayList = new PlayList();

        public PlayList FPlayList
        {
            get { return fPlayList; }
            set { fPlayList = value; }
        }

        private string animate="";
        public string Animate
        {
            get { return animate; }
            set { animate = value; }
        }

        private string qrcode = "";
        public string QRCode
        {
            get { return qrcode; }
            set { qrcode = value; }
        }

        //辅助区域展示的前十大和推荐所有的鞋子
        private List<TopShoes> defShowShoesList = null;
        public List<TopShoes> DefShowShoesList
        {
            get { return defShowShoesList; }
            set { defShowShoesList = value; }
        }

        public ChromiumWebBrowser browser;
        public UCBrowser()
        {
            InitializeComponent();
            try
            {
                string cacheDir = Application.StartupPath + @"\Caches\";
                if (!Directory.Exists(cacheDir))
                {
                    Directory.CreateDirectory(cacheDir);
                }
            }
            catch(Exception e)
            {
                InitConst.WriteToLogFile(e.Message + e.StackTrace);
            }
            try
            {
                string browserPath = Application.StartupPath + @"\Config\UCBrowser.xml";
                XmlConfig xc = new XmlConfig(browserPath);
                XmlData xd = xc.GetXmlNodeContent("ServerUrl");
                UCBrowserURL = xd.NodeValue;


                LoadDefShowShoes();
                InitAnimate();
            }
            catch (Exception ex)
            {
                MessageBox.Show("读取配置出现异常：" + ex.Message, "提示");
            }
            try
            {
                Init();
            }
            catch (Exception e)
            {
                InitConst.WriteToLogFile(e.Message + e.StackTrace);
            }
        }

        private void Init()
        {
            if (!Cef.IsInitialized)
            {
                var settings = new CefSettings();
                settings.RegisterScheme(new CefCustomScheme
                {
                    SchemeName = "animate",
                    SchemeHandlerFactory = new AnimateSchemeHandlerFactory(UCBrowserURL)
                });
                Cef.Initialize(settings);
                browser = new ChromiumWebBrowser(Application.StartupPath + @"\pages\index.html")
                {
                    Dock = DockStyle.Fill,
                    KeyboardHandler = new CEFKeyBoardHander(),
                };
                this.Controls.Add(browser);

                browser.RegisterAsyncJsObject("NUITE", new JsObject(this));
            }
        }

        private void InitAnimate()
        {
            //获取后台设置的动画名称
            string animateName = null;
            string qrcodePath = null;
            try
            {
                string strURL = UCBrowserURL + "setup/v2?shopSeq=" + InitConst.ShopID;
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(strURL);
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                StreamReader myreader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
                string responseText = myreader.ReadToEnd();
                myreader.Close();
                JObject jobject = JObject.Parse(responseText);
                int code = jobject.GetValue("code").Value<int>();
                string animate = jobject.GetValue("animate").Value<string>();
                string qrcode = jobject.GetValue("qrcode").Value<string>();
                if (code == 1)
                {
                    animateName = animate;
                    qrcodePath = qrcode;
                    //写入缓存文件
                    FileHelper.CreateFile(CacheSettingTxt, responseText);
                }
                else
                {
                    InitConst.WriteToLogFile(responseText);
                }
            }
            catch (Exception ex)
            {
                InitConst.WriteToLogFile(ex.Message + ex.StackTrace);
            }

            //如果请求失败，未设置数据，从缓存中取数据
            if (animateName == null || qrcodePath == null)
            {
                string cacheResponseTxt = FileHelper.GetTxtFileContent(CacheSettingTxt);
                if (cacheResponseTxt.Length > 0)
                {
                    JObject jobject = JObject.Parse(cacheResponseTxt);
                    animateName = jobject.GetValue("animate").Value<string>();
                    qrcodePath = jobject.GetValue("qrcode").Value<string>();
                }
                else
                {
                    animateName = "";
                    qrcodePath = "";
                }
            }

            QRCode = qrcodePath;
            Animate = animateName;
        }

        private System.Timers.Timer timer;
        public void Closing()
        {
            try
            {
                Cef.Shutdown();

                timer.Dispose();
                timer.Close();
            }
            catch (Exception e)
            {
                InitConst.WriteToLogFile(e.Message + e.StackTrace);
            }
        }

        //启动定时任务获取前十大的鞋子
        private void LoadDefShowShoes()
        {
            timer = new System.Timers.Timer();
            timer.Enabled = true;
            timer.Interval = 5 * 60 * 1000;//执行间隔时间,单位为毫秒;此时时间间隔为5分钟  
            timer.Elapsed += new ElapsedEventHandler(GetFromServer);
            timer.Start();
            InitConst.WriteToLogFile("timer start!!!");

            //启动后立即执行一次
            GetFromServer(null, null);
        }

        private void GetFromServer(object source, ElapsedEventArgs e)
        {
            timer.Enabled = false;
            string responseData;
            try
            {
                string strURL = UCBrowserURL + "top?shopSeq=" + InitConst.ShopID;

                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(strURL);
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                StreamReader myreader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
                string responseText = myreader.ReadToEnd();
                myreader.Close();

                //对返回数据进行gzip解压
                byte[] zipByteArray = StrToHexByte(responseText);
                byte[] unzipByteArray = Decompress(zipByteArray);
                responseData = Encoding.Default.GetString(unzipByteArray);
                FileHelper.CreateFile(CacheTopTxt, responseData);
            }
            catch (Exception exception)
            {
                InitConst.WriteToLogFile("shopId:::" + InitConst.ShopID + "error::::" + exception.Message + exception.StackTrace);

                //从缓存中读取内容
                responseData = FileHelper.GetTxtFileContent(CacheTopTxt);
                if (responseData.Length <= 0)
                {
                    responseData = "[]";
                }
            }
            Console.WriteLine(responseData);
            //将返回数据解析为json数组
            JArray jArray = JArray.Parse(responseData);
            List<TopShoes> TimerShowShoesList = new List<TopShoes>();

            //循环数组，查询本地所有资源
            foreach (var jToken in jArray)
            {
                TopShoes topShoes = new TopShoes();
                JObject shoeObj = JObject.Parse(jToken.Value<string>());
                string shoesId = shoeObj.GetValue("ID").Value<string>();
                string shoesDes = shoeObj.GetValue("DES").Value<string>();
                string periodSeq = shoeObj.GetValue("PERIOD").Value<string>();
                topShoes.ShoesID = shoesId;
                topShoes.ShoesDES = shoesDes;
                topShoes.PeriodSeq = periodSeq;
                string result = null;
                DataTable shoeData = busDisplayClient.GetPlayResultByShoeId(shoesId, ref result);
                if (result == null && shoeData != null)
                {
                    topShoes.PlayData = shoeData;
                }
                else
                {
                    topShoes.PlayData = new DataTable();
                }
                if (File.Exists(Application.StartupPath+@"\Resource\ShoesResource\ShoesIcons\" + periodSeq + @"\" + shoesId + ".jpg"))
                {
                    TimerShowShoesList.Add(topShoes);
                }
            }
            DefShowShoesList = TimerShowShoesList;
            timer.Enabled = true;
        }

        //hex字符串转byte数组
        private static byte[] StrToHexByte(string hexString)
        {
            hexString = hexString.Replace(" ", "");
            if ((hexString.Length % 2) != 0)
                hexString += " ";
            byte[] returnBytes = new byte[hexString.Length / 2];
            for (int i = 0; i < returnBytes.Length; i++)
                returnBytes[i] = Convert.ToByte(hexString.Substring(i * 2, 2), 16);
            return returnBytes;
        }

        //对byte数组进行GZIP解压
        public static byte[] Decompress(byte[] zippedData)
        {
            MemoryStream ms = new MemoryStream(zippedData);
            GZipStream compressedzipStream = new GZipStream(ms, CompressionMode.Decompress);
            MemoryStream outBuffer = new MemoryStream();
            byte[] block = new byte[1024];
            while (true)
            {
                int bytesRead = compressedzipStream.Read(block, 0, block.Length);
                if (bytesRead <= 0)
                    break;
                else
                    outBuffer.Write(block, 0, bytesRead);
            }
            compressedzipStream.Close();
            return outBuffer.ToArray();
        }
    }

    public class JsObject
    {
        private UCBrowser ucBrowser = null;

        public JsObject(UCBrowser ucBrowser)
        {
            this.ucBrowser = ucBrowser;
        }

        //获取环境运行路径
        [DebuggerHidden]
        public string GetLocalPath()
        {
            string localPathScript = "file://" + Application.StartupPath.Replace(@"\", @"/");
            return localPathScript;
        }

        //获取播放视频列表
        [DebuggerHidden]
        public string GetVideos()
        {
            return ToJsonString(ucBrowser.DtVideos);
        }

        //获取背景音乐
        [DebuggerHidden]
        public string GetMp3s()
        {
            return ToJsonString(ucBrowser.DtMp3s);
        }

        //获取试穿时的鞋子列表
        [DebuggerHidden]
        public string GetTryShoes()
        {
            return ToJsonString(ucBrowser.FPlayList);
        }

        //获取前十大的鞋子列表
        [DebuggerHidden]
        public string GetTopShoes()
        {
            return ToJsonString(ucBrowser.DefShowShoesList);
        }

        //获取动画
        [DebuggerHidden]
        public string GetAnimate()
        {
            return ToJsonString(ucBrowser.Animate);
        }

        private string ToJsonString(Object obj)
        {
            string jsonString = JsonConvert.SerializeObject(obj);
            return jsonString.Replace(@"\\", @"/");
        }
    }

    public class TopShoes
    {
        public string ShoesID { get; set; }	          //鞋子货号
        public string ShoesDES { get; set; }	      //鞋子描述
        public string PeriodSeq { get; set; }	      //鞋子波次序号
        public DataTable PlayData { get; set; }       //素材资源
    }

    public class FileHelper
    {
        /// <summary>
        /// 获取指定文件内容
        /// 注：这里适用于读取文本类型文件
        /// </summary>
        public static string GetTxtFileContent(string fileName)
        {
            if (!File.Exists(fileName)) //文件不存在
                return "";
            FileStream fs = new FileStream(fileName, FileMode.Open, FileAccess.Read);
            StreamReader reader = new StreamReader(fs, Encoding.Default);
            string result = "";
            string line = "";
            while ((line = reader.ReadLine()) != null)
            {
                result += line;
            }
            reader.Close();
            fs.Close();
            return result;
        }

        /// <summary>
        /// 创建文件
        /// </summary>
        /// <param name="path">文件全路径</param>
        /// <param name="content">写入内容</param>
        public static void CreateFile(string fileName, string content = null)
        {
            FileStream fs = new FileStream(fileName, FileMode.Create, FileAccess.Write);
            if (content != null)
            {
                StreamWriter sw = new StreamWriter(fs);
                sw.WriteLine(content);
                sw.Close();
            }
            fs.Close();
        }
    }
}
