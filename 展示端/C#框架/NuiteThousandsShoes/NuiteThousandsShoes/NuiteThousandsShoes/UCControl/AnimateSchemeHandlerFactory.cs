using CefSharp;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Windows.Forms;

namespace NuiteThousandsShoes
{
    class AnimateSchemeHandlerFactory : ISchemeHandlerFactory
    {
        public const string SchemeName = "animate";

        private static string DataURL;

        public AnimateSchemeHandlerFactory(string UCBrowserURL)
        {
            DataURL = UCBrowserURL.Replace("show-admin/shop-data/", "")+ "picture/shop-model/";
        }

        public IResourceHandler Create(IBrowser browser, IFrame frame, string schemeName, IRequest request)
        {
            Console.WriteLine("Here is the result:" + request.Url);
            string animatePicName = request.Url.Replace("/", "").Replace("animate:", "");
            string imagePath = Application.StartupPath + @"\Resource\Animation\";
            if (!Directory.Exists(imagePath))
            {
                Directory.CreateDirectory(imagePath);
            }
            string imageName = imagePath + animatePicName;
            if (!File.Exists(imageName))
            {
                try
                {
                    HttpDownloadFile(DataURL + animatePicName, imageName);
                }
                catch (Exception e)
                {
                    InitConst.WriteToLogFile(e.Message + e.StackTrace);
                }
            }
            if (File.Exists(imageName))
            {
                FileStream fs = File.OpenRead(imageName);
                return ResourceHandler.FromStream(fs, "png");
            }
            else
            {
                InitConst.WriteToLogFile(animatePicName+"文件下载失败！");
                return ResourceHandler.FromString("", "png");
            }
        }

        private static void HttpDownloadFile(string url, string path)
        {
            // 设置参数
            HttpWebRequest request = WebRequest.Create(url) as HttpWebRequest;
            //发送请求并获取相应回应数据
            HttpWebResponse response = request.GetResponse() as HttpWebResponse;
            //直到request.GetResponse()程序才开始向目标网页发送Post请求
            Stream responseStream = response.GetResponseStream();
            //创建本地文件写入流
            Stream stream = new FileStream(path, FileMode.Create);
            byte[] bArr = new byte[1024];
            int size = responseStream.Read(bArr, 0, (int)bArr.Length);
            while (size > 0)
            {
                stream.Write(bArr, 0, size);
                size = responseStream.Read(bArr, 0, (int)bArr.Length);
            }
            stream.Close();
            responseStream.Close();
        }
    }
}
