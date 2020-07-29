using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Windows.Forms;
using System.Drawing;
using RFIDLibraryEx;
namespace NuiteThousandsShoes
{
    /// <summary>
    /// 试鞋播放展示控制器
    /// </summary>
    public class ConDisplayClient_Play
    {
        private DataTable fDTForMp3 = null;//背景音乐列表
        private DataTable fDTForPlayData = null;//需要播放的图片列表
        private string fCurPlayID = "0";//当前播放的图片列表id
        private string fCurShoeID = "0";//当前播放的鞋子id
        private int fCurMp3Index = 0;//当前播放的音频列表index
        private bool fPlayState = false;//背景音乐是否在播放
        private bool fPlayPicState = false;//图片是否在播放
        private BusDisplayClient fBusDisplayClient = new BusDisplayClient();//获取背景音乐数据类
        //private VLCLibrary.VLCControl fVLCControl = new VLCLibrary.VLCControl();//视频和音频播放用的控件
        //private UCPicAnimator.UCPicAnimator fUCPicAnimator = null;//图片播放控件需要外部赋值
        //private ConDisplayClient_Play_FirstChange fConDisplayClient_Play_FirstChange = new ConDisplayClient_Play_FirstChange();

        //硬件控件
        private RFIDControl fRFIDControl = new RFIDControl();
        
        private FrmWarn fFrmWarn = null;
        //错误消息
        private string fError = null;
     
        //当前播放序号
        private int fPlayID = 0;

        //当前硬件信息
        private HardInfo fCurHardInfo = null;
        //当前硬件是否激活
        private bool fIsActiveHard = false;
        //监听标签计时器
        private Timer fMonitorTimer = null;
        //监听是否过期计时器
        private Timer fLoseTimer = null;
        //默认视频获取
        private TryShoes fDefultTryShoes = null;

        //当前鞋子队列(用于播放展示)
        private PlayList fPlayList = new PlayList();
        private List<string> fErrorRFID = new List<string>();
        private Form fFrmDefault = null;

        public Form FrmDefault
        {
            get { return fFrmDefault; }
            set { fFrmDefault = value; }
        }

        private string fErrorStr = "";

        System.Drawing.Rectangle rect =System.Windows.Forms.Screen.PrimaryScreen.Bounds;

        public string ErrorStr
        {
            get { return fErrorStr; }
            set { fErrorStr = value; }
        }
        private UCBrowser browserObj = null;
        public void InitData(UCBrowser browserObj)
        {
            this.browserObj = browserObj;

            //组织默认视频内容并初始化视频播放素材
            fDefultTryShoes = GetTryShoes("-1");
            this.browserObj.DtVideos = fDefultTryShoes.PlayData;
            // fConDisplayClient_Play_FirstChange.Clear();
            //fConDisplayClient_Play_FirstChange.InitData(pic);
            fErrorStr = "";
            //初始化视频控件
            //fVLCControl.Init(Application.StartupPath, ucVideo);
            //fVLCControl.StrLogFilePath = InitConst.QSAppPath + @"\Log\";
            //监听视频结束事件
            //fVLCControl.PlayEnd += new EventHandler(VLCControl_PlayEnd);
            //获取鞋子背景音乐素材
            fDTForMp3 = fBusDisplayClient.GetPlayResult("-2", ref fErrorStr);
            this.browserObj.DtMp3s = fDTForMp3;

            ////启动计时器开始监听标签
            //if (fMonitorTimer == null)
            //{
            //    fMonitorTimer = new Timer();
            //    fMonitorTimer.Interval = 100;
            //    fMonitorTimer.Tick += new EventHandler(fMonitorTimer_Tick);
            //}
            //fMonitorTimer.Start();
        }

       
        #region 背景音乐播放部分
        ////播放结束事件
        //private void VLCControl_PlayEnd(object sender, EventArgs e)
        //{
        //    try
        //    {
        //        if (fCanPlayMp3 == false) return;
        //        //更改播放序号，播放新的素材
        //        if (fCurMp3Index < fDTForMp3.Rows.Count - 1)
        //        {
        //            fCurMp3Index++;
        //        }
        //        else
        //        {
        //            fCurMp3Index = 0;
        //        }
        //        //设置播放状态为停止
        //        fPlayState = false;

        //        //继续播放下一个
        //        PlayMp3();
        //    }
        //    catch { }
        //}

        ///// <summary>
        ///// 播放当前鞋子对象素材
        ///// </summary>
        //private void PlayMp3()
        //{
        //    try
        //    {
        //        if (fDTForMp3 == null || fDTForMp3.Rows.Count == 0) return;
        //        if (fCanPlayMp3 == false) return;
        //        if (!fPlayState)
        //        {
        //            //获取播放的素材文件
        //            string filepath = Application.StartupPath + fDTForMp3.Rows[fCurMp3Index]["RelativeURL"].ToString() ;
        //            filepath = filepath.Replace("/", @"\");
        //            if (filepath[filepath.Length - 1] !='\\')
        //            {
        //                filepath += "\\";
        //            }
        //            filepath += fDTForMp3.Rows[fCurMp3Index]["FileName"].ToString();
        //            //设置播放的素材文件
        //            fVLCControl.SetVedioFile(filepath);
        //            //播放相关的素材文件
        //            fVLCControl.Play();
        //            fVLCControl.SetVolume(100);
        //            fPlayState = !fPlayState;
        //        }

        //    }
        //    catch
        //    {

        //    }
        //}
        //private void StopMp3()
        //{
        //    try
        //    {
        //        fVLCControl.SetVolume(0);
        //        fVLCControl.Pause();
             
        //    }
        //    catch (Exception ex)
        //    {
        //        InitConst.WriteToLogFile("播放鞋子背景音乐出错：StopMp3" + ex.Message);
        //    }
        //    fPlayState = false;
        //    fCurMp3Index = 0;
        //}
        //#endregion

        //#region 鞋子素材播放部分
        //void fUCPicAnimator_DrawCompleted(object sender, EventArgs e)
        //{
        //    try
        //    {
        //        //设置播放状态为停止
        //        fPlayPicState = false;
        //        if (fDTForPlayData.Rows.Count > 0)
        //        {
        //            //更改播放序号，播放新的素材
        //            DataRow[] drArray = fDTForPlayData.Select("ID>" + fCurPlayID);
        //            if (drArray.Length > 0)
        //            {
        //               fCurPlayID = drArray[0]["ID"].ToString();
        //            }
        //            else
        //            {
        //                fCurPlayID = fDTForPlayData.Rows[0]["ID"].ToString();
        //            }
                 
        //            //继续播放下一个
        //            PlayPic();
        //        }
        //    }
        //    catch { }
        //}
        

        /// <summary>
        /// 播放当前鞋子对象素材
        /// </summary>
        //private void PlayPic()
        //{
        //    try
        //    {
        //        if (fDTForPlayData == null || fDTForPlayData.Rows.Count == 0) return;
        //        if (!fPlayPicState)
        //        {
        //            string filepath = "";
        //            int speedCnt = 1;
        //            int flag =0;
        //            DataRow[] drArray = fDTForPlayData.Select("ID=" + fCurPlayID);
        //            if (drArray.Length > 0)
        //            {
        //                filepath = Application.StartupPath + drArray[0]["RelativeURL"].ToString();
        //                filepath = filepath.Replace("/", @"\");
        //                if (filepath[filepath.Length - 1] != '\\')
        //                {
        //                    filepath += "\\";
        //                }
        //                filepath += drArray[0]["Name"].ToString();
        //                speedCnt = Convert.ToInt32(drArray[0]["SpeedCount"].ToString());
        //                flag = Convert.ToInt32(drArray[0]["Flag"].ToString());
        //                fCurShoeID = drArray[0]["ShoesSEQ"].ToString();
        //            }
        //            else
        //            {
        //                fCurPlayID = fDTForPlayData.Rows[0]["ID"].ToString();
        //                fCurShoeID = fDTForPlayData.Rows[0]["ShoesSEQ"].ToString();
        //                filepath = Application.StartupPath + fDTForPlayData.Rows[0]["RelativeURL"].ToString();
        //                filepath = filepath.Replace("/", @"\");
        //                if (filepath[filepath.Length - 1] != '\\')
        //                {
        //                    filepath += "\\";
        //                }
        //                filepath += fDTForPlayData.Rows[0]["Name"].ToString();
        //                speedCnt =  Convert.ToInt32(fDTForPlayData.Rows[0]["SpeedCount"].ToString());
        //                flag = Convert.ToInt32(fDTForPlayData.Rows[0]["Flag"].ToString());
        //            }
        //            //获取播放的素材文件
        //            filepath = filepath.Replace("/", @"\");
        //            //InitConst.WriteToLogFile(filepath);
        //            if (System.IO.File.Exists(filepath) == false)
        //            {
        //                drArray = fDTForPlayData.Select("ID>" + fCurPlayID);
        //                if (drArray.Length > 0)
        //                {
        //                    fCurPlayID = drArray[0]["ID"].ToString();
        //                    fCurShoeID = drArray[0]["ShoesSEQ"].ToString();
        //                }
        //                else
        //                {
        //                    fCurPlayID = fDTForPlayData.Rows[0]["ID"].ToString();
        //                    fCurShoeID = fDTForPlayData.Rows[0]["ShoesSEQ"].ToString();
        //                }
        //                return;
        //            }
        //            //设置播放的素材文件
        //            Bitmap img = Image.FromFile(filepath) as Bitmap;
        //            UCPicAnimator.AnimateType animateType = (UCPicAnimator.AnimateType)flag;
        //            if (animateType == UCPicAnimator.AnimateType.Animator00)
        //                animateType = (UCPicAnimator.AnimateType)(DateTime.Now.Millisecond % 10 + 1);
        //            fUCPicAnimator.SetImage(img, speedCnt, PictureBoxSizeMode.StretchImage);
        //            fPlayPicState = !fPlayPicState;
        //            drArray = fDTForPlayData.Select("ShoesSEQ=" + fCurShoeID);
        //            //if (drArray.Length == 1)
        //            //    animateType = (UCPicAnimator.AnimateType)(DateTime.Now.Millisecond % 10 + 1);
        //            fUCPicAnimator.SetImage(img, speedCnt, PictureBoxSizeMode.StretchImage);
        //            fUCPicAnimator.DrawAnimator(animateType);
        //            PlayShoesTipMp3(drArray.Length);
        //        }

        //    }
        //    catch (Exception ex)
        //    {
        //        InitConst.WriteToLogFile("开始图片播放出错：PlayPic" + ex.Message);
        //    }
        //}
        //private void PlayShoesTipMp3(int shoeCnt)
        //{
        //    try
        //    {
        //        fVLCControl.SetVolume(0);
        //        fConDisplayClient_Play_FirstChange.PlayShoesChange(fCurShoeID, shoeCnt);
        //    }
        //    catch (Exception ex)
        //    {
        //        InitConst.WriteToLogFile("图片切换提示音播放出错：PlayShoesTipMp3" + ex.Message);
        //    }
        //    fVLCControl.SetVolume(100);
        //}
        //private void StopPic()
        //{
        //    try
        //    {
        //        fUCPicAnimator.PauseDraw();
        //        fUCPicAnimator.CancelDraw();
        //        fUCPicAnimator.ClearBackImage();
        //    }
        //    catch (Exception ex)
        //    {
        //        InitConst.WriteToLogFile("停止图片播放出错：StopPic" + ex.Message);
        //    }
        //    fPlayPicState = false;
        //    fCurPlayID = "0";
        //}
        #endregion

        //****************************************************硬件初始化相关*************************************************

        /// <summary>
        /// 初始化reader硬件并且获取reader状态
        /// </summary>
        /// <returns></returns>
        public bool InitAndGetReaderStatus()
        {
            bool result = false;
            if (fIsActiveHard == true && fRFIDControl.GetVersion())//reader是连接着的
            {
                result = true;
                if (fFrmWarn != null)
                {
                    fFrmWarn.Visible = false;
                }
            }
            else//reader未能连接
            {
                fRFIDControl.Close();
                GetHardInfo();
                fIsActiveHard = ActiveHard();
                if (fIsActiveHard)
                {
                    fIsActiveHard = true;
                    MonitorReaderData();//连接成功监听reader数据
                }
                else
                {
                    //右下角弹窗显示提示信息硬件没有连接上
                    if (fFrmWarn == null)
                    {
                        fFrmWarn = new FrmWarn();
                        fFrmWarn.Owner = fFrmDefault;
                        fFrmWarn.Show();
                    }
                    else
                        fFrmWarn.Visible = true;
                    fIsActiveHard = false;
                }
            }
            return result;
        }

        /// <summary>
        /// 获取硬件信息
        /// </summary>
        /// <returns></returns>
        private void GetHardInfo()
        {
            try
            {
                DataTable dt = fBusDisplayClient.GetHardInfoResult(ref fError);
                if (dt != null)
                {
                    int i = 0;
                    for (i = 0; i < dt.Rows.Count; i++)
                    {
                        if (dt.Rows[i]["FK_ShopBaseInfo_SEQ"].ToString() == InitConst.ShopID)
                        {
                            break;
                        }
                    }
                    fCurHardInfo = new HardInfo();
                    fCurHardInfo.ShopSeq = Convert.ToInt32(dt.Rows[i]["FK_ShopBaseInfo_SEQ"].ToString());
                    fCurHardInfo.HardwareID = dt.Rows[i]["HardwareID"].ToString();
                    fCurHardInfo.HardwareName = dt.Rows[i]["HardwareName"].ToString();
                    fCurHardInfo.UseAntennaPort = dt.Rows[i]["UseAntennaPort"].ToString();
                    fCurHardInfo.ReaderType = Convert.ToInt32(dt.Rows[i]["ReaderType"].ToString());
                    fCurHardInfo.IP = dt.Rows[i]["IP"].ToString();
                    fCurHardInfo.Port = dt.Rows[i]["Port"].ToString();
                    fCurHardInfo.OutPutPower = Convert.ToInt32(dt.Rows[i]["OutPutPower"].ToString());
                    fCurHardInfo.RFIDEffectTime = Convert.ToInt32(dt.Rows[i]["RFIDEffectTime"].ToString());
                    fCurHardInfo.Minimumfrequency = Convert.ToInt32(dt.Rows[i]["Minimumfrequency"].ToString());
                    fCurHardInfo.Maxmumfrequency = Convert.ToInt32(dt.Rows[i]["Maxmumfrequency"].ToString());
                    fCurHardInfo.Frequencyspace = Convert.ToSingle(dt.Rows[i]["Frequencyspace"].ToString());
                    fCurHardInfo.Bleepermode = Convert.ToInt32(dt.Rows[i]["Bleepermode"].ToString());
                    fCurHardInfo.ProFileMode = Convert.ToInt32(dt.Rows[i]["ProFileMode"].ToString());
                    fCurHardInfo.ReturnLoss = Convert.ToInt32(dt.Rows[i]["ReturnLoss"].ToString());
                }
            }
            catch
            {

            }
        }

        /// <summary>
        /// 激活硬件
        /// </summary>
        /// <returns>是否成功</returns>
        private bool ActiveHard()
        {
            bool result = false;
            try
            {
                if (fCurHardInfo == null) return result;
                //初始化硬件参数
                fRFIDControl.Init(fCurHardInfo.IP, fCurHardInfo.Port);
                //设置过期时间
                fRFIDControl.TimeForOutTag = fCurHardInfo.RFIDEffectTime;
                //设置射频频率
                fRFIDControl.SetOutPutPower((byte)fCurHardInfo.OutPutPower);
                //设置频点
                fRFIDControl.SetFreqPointValue(fCurHardInfo.Minimumfrequency, fCurHardInfo.Maxmumfrequency, fCurHardInfo.Frequencyspace);
                //设置链路
                fRFIDControl.SetProfile((RFIDControl.ProFileMode)fCurHardInfo.ProFileMode);
                //设置蜂鸣器
                fRFIDControl.SetBeeperMode((RFIDControl.BeeperMode)fCurHardInfo.Bleepermode);
                //回波损耗阈值
                fRFIDControl.SetReturnLoss(fCurHardInfo.ReturnLoss);
                //设置天线
                string[] UID = fCurHardInfo.UseAntennaPort.Split(',');
                foreach (string key in UID)
                {
                    fRFIDControl.SetWorkAntennaEnable(Byte.Parse(key));
                }
                //启动
                result = fRFIDControl.StartServer(RFIDControl.ServerMode.NetMode);
                string errorStr = "启动结果：" + result.ToString();
                errorStr += "硬件IP和端口：" + fCurHardInfo.IP + "  " + fCurHardInfo.Port;
                errorStr += "硬件过期时间：" + fCurHardInfo.RFIDEffectTime;
                errorStr += "硬件射频功率：" + fCurHardInfo.OutPutPower.ToString();
                errorStr += "硬件频点：" + fCurHardInfo.Minimumfrequency.ToString() + " " + fCurHardInfo.Maxmumfrequency.ToString() + " " + fCurHardInfo.Frequencyspace.ToString();
                errorStr += "硬件链路：" + fCurHardInfo.ProFileMode.ToString();
                errorStr += "硬件蜂鸣器：" + fCurHardInfo.Bleepermode.ToString();
                errorStr += "硬件回波损耗阈值：" + fCurHardInfo.ReturnLoss.ToString();
                errorStr += "硬件天线值：" + fCurHardInfo.UseAntennaPort.ToString();
                //fMotiorCommonLibrary.CurStartDateTime = DateTime.Now;
                //fMotiorCommonLibrary.CurSystemName = MotiorCommonLibrary.MotiorCommonLibrary.SystemName.前台试鞋系统;
                //fMotiorCommonLibrary.CurModelName = MotiorCommonLibrary.MotiorCommonLibrary.ModelName.硬件监控;
                //fMotiorCommonLibrary.CurMethodName = "ActiveHard";
                //fMotiorCommonLibrary.OnlyID = InitConst.ShopID;
                //fMotiorCommonLibrary.CurErrorCode = MotiorCommonLibrary.MotiorCommonLibrary.ErrorEnum.默认没有异常;
                //fMotiorCommonLibrary.CurErrorStr = errorStr;
                //fMotiorCommonLibrary.CurEndDateTime = DateTime.Now;
                //fMotiorCommonLibrary.WriteToSystemModelUseLog();
            }
            catch
            {
                result = false;
            }
            return result;
        }

        /// <summary>
        /// 监听reader数据
        /// </summary>
        public void MonitorReaderData()
        {
            //启动计时器开始监听标签
            if (fMonitorTimer == null)
            {
                fMonitorTimer = new Timer();
                fMonitorTimer.Interval = 100;
                fMonitorTimer.Tick += new EventHandler(fMonitorTimer_Tick);
            }
            else
            {
                fMonitorTimer.Stop();
            }
            fMonitorTimer.Start();

            //启动计时器监听标签是否过期
            if (fLoseTimer == null)
            {
                fLoseTimer = new Timer();
                fLoseTimer.Interval = 1000;
                fLoseTimer.Tick += new EventHandler(fLoseTimer_Tick);
            }
            else
            { fLoseTimer.Stop(); }
            fLoseTimer.Start();
        }
        //******************************************************************************************************************

        //****************************************************标签相关处理**************************************************
        /// <summary>
        /// 计时器监听标签信息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void fMonitorTimer_Tick(object sender, EventArgs e)
        {
            fMonitorTimer.Enabled = false;
            try
            {
                if (fRFIDControl == null) return;
                if (fRFIDControl.BDelListTagInfo)
                {
                    return;
                }
                //bool bSetData = false;
                if (fRFIDControl != null && fRFIDControl.ListTagInfo != null && fRFIDControl.ListTagInfo.Count > 0)
                {
                    //if (fMotiorCommonLibrary == null) return;
                    //fMotiorCommonLibrary.CurStartDateTime = DateTime.Now;
                    //fMotiorCommonLibrary.CurSystemName = MotiorCommonLibrary.MotiorCommonLibrary.SystemName.前台试鞋系统;
                    //fMotiorCommonLibrary.CurModelName = MotiorCommonLibrary.MotiorCommonLibrary.ModelName.RFID检测;
                    //fMotiorCommonLibrary.CurMethodName = "fMonitorTimer_Tick";
                    //fMotiorCommonLibrary.OnlyID = InitConst.ShopID;
                    string errorStr = "";
                    if (fCurHardInfo == null) return;
                    if (fPlayList == null) return;
                    //if (fVLCControl == null) return;
                    for (int i = 0; i < fRFIDControl.ListTagInfo.Count; i++)
                    {
                        if (fRFIDControl.ListTagInfo[i] == null) continue;
                        if (fRFIDControl.BDelListTagInfo)
                        {
                            continue;
                        }
                        if (fRFIDControl.ListTagInfo[i].LastTime == "") continue;
                        if (Convert.ToDateTime(fRFIDControl.ListTagInfo[i].LastTime).AddSeconds(fCurHardInfo.RFIDEffectTime) < DateTime.Now)
                        {
                            continue;
                        }
                        if (!fPlayList.IsContaint(fRFIDControl.ListTagInfo[i].TagID.ToString()))
                        {
                            TryShoes ts = GetTryShoes(fRFIDControl.ListTagInfo[i].TagID.ToString());
                            if (ts != null)
                            {
                                ts.RFID = fRFIDControl.ListTagInfo[i].TagID.ToString();
                                ts.starttimes = ts.endtimes = Convert.ToDateTime(fRFIDControl.ListTagInfo[i].LastTime);
                                fPlayList.Insert(ts);
                               
                                //bSetData = true;
                                errorStr = "加入标签：" + fRFIDControl.ListTagInfo[i].TagID + "  " + fRFIDControl.ListTagInfo[i].LastTime;
                                //fMotiorCommonLibrary.CurErrorCode = MotiorCommonLibrary.MotiorCommonLibrary.ErrorEnum.默认没有异常;
                                //fMotiorCommonLibrary.CurErrorStr = errorStr;
                                if (fErrorRFID.Contains(fRFIDControl.ListTagInfo[i].TagID) == false)
                                {
                                    fErrorRFID.Add(fRFIDControl.ListTagInfo[i].TagID);
                                    //fMotiorCommonLibrary.CurEndDateTime = DateTime.Now;
                                    //fMotiorCommonLibrary.WriteToSystemModelUseLog();
                                }
                            }
                            else
                            {
                                errorStr = "标签：" + fRFIDControl.ListTagInfo[i].TagID + "  " + fRFIDControl.ListTagInfo[i].LastTime + "未查到对应的鞋子信息";
                                //fMotiorCommonLibrary.CurErrorCode = MotiorCommonLibrary.MotiorCommonLibrary.ErrorEnum.其他异常;
                                //fMotiorCommonLibrary.CurErrorStr = errorStr;
                                if (fErrorRFID.Contains(fRFIDControl.ListTagInfo[i].TagID) == false)
                                {
                                    fErrorRFID.Add(fRFIDControl.ListTagInfo[i].TagID);
                                    //fMotiorCommonLibrary.CurEndDateTime = DateTime.Now;
                                    //fMotiorCommonLibrary.WriteToSystemModelUseLog();
                                }
                            }
                        }
                        else
                        {
                            fPlayList.UpdateTime(fRFIDControl.ListTagInfo[i].TagID.ToString(), fRFIDControl.ListTagInfo[i].LastTime.ToString());
                        }
                        this.browserObj.FPlayList = fPlayList;
                    }
                    //if (bSetData)
                    //{
                    //    SetData();
                    //}
                }
                else
                {
                    if (fErrorRFID.Count > 0)
                        fErrorRFID.Clear();
                }
            }
            catch (Exception ex)
            {
                InitConst.WriteToLogFile("计时器监听标签信息：fMonitorTimer_Tick" + ex.Message);
            }
            finally
            {
                fMonitorTimer.Enabled = true;

            }
        }
        //private void SetData()
        //{
        //    bool bSuc = false;
        //    if (fPlayList != null)
        //    {
        //        bSuc = fPlayList.SetToShareData();
        //    }
        //    (fFrmDefault as FrmDisplayClient_Defult).SetFormSize(!bSuc);
        //    if (fVLCControl == null) return;
        //    if (bSuc)
        //    {
        //        fVLCControl.SetVolume(0);
        //        if (fFrmWarn != null)
        //            fFrmWarn.Visible = false;
        //    }
        //    else
        //        fVLCControl.SetVolume(100);

        //    //fPlayList.GetShareData();
        //}

        /// <summary>
        /// 计时器监听标签过期
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void fLoseTimer_Tick(object sender, EventArgs e)
        {
            fLoseTimer.Enabled = false;
            try
            {
                if (fPlayList.ShoesPlayList.Count > 0)
                {
                    for (int i = fPlayList.ShoesPlayList.Count - 1; i >= 0; i--)
                    {
                        if (Convert.ToDateTime(fPlayList.ShoesPlayList[i].endtimes).AddSeconds(fCurHardInfo.RFIDEffectTime) < DateTime.Now)
                        {
                            fPlayList.InsertLose(fPlayList.ShoesPlayList[i]);
                            fPlayList.RemoveBy(fPlayList.ShoesPlayList[i]);
                            this.browserObj.FPlayList = fPlayList;
                           // SetData();
                            //InitConst.WriteToRFIDLogFile("移除标签：" + fPlayList.fShoesPlayList[i].RFID + "  " + fPlayList.fShoesPlayList[i].endtimes);
                        }
                    }

                }
                else
                {
                    fPlayList.SaveLoseListToFile();
                }
            }
            catch
            {
            }
            finally
            {
                fLoseTimer.Enabled = true;
            }
        }

        /// <summary>
        /// 计时器监听标签信息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        //private void fMonitorTimer_Tick(object sender, EventArgs e)
        //{
        //    fMonitorTimer.Enabled = false;
        //    try
        //    {
        //       fDTForPlayData = fPlayList.GetShareData();
        //       if (fDTForPlayData == null || fDTForPlayData.Rows.Count == 0)
        //       {
        //          // fConDisplayClient_Play_FirstChange.Clear();
        //          // fCanPlayMp3 = false;
        //          // StopMp3();
        //          // StopPic();
        //       }
        //       else
        //       {
        //          // fCanPlayMp3 = true;
        //           DataRow[] drArray = fDTForPlayData.Select("ID=" + fCurPlayID);
        //         if (drArray.Length > 0)
        //         {
        //         }
        //         else
        //         {
        //             fCurPlayID = fDTForPlayData.Rows[0]["ID"].ToString();
        //             //PlayMp3();
        //             //PlayPic();
        //         }
               
        //       }
        //    }
        //    catch(Exception ex)
        //    {
        //        InitConst.WriteToLogFile("播放鞋子监听出错：fMonitorTimer_Tick"+ex.Message);
        //    }
        //    finally
        //    {
        //        fMonitorTimer.Enabled = true;
        //    }
        //}

        /// <summary>
        /// 根据标签组织鞋子对象
        /// </summary>
        /// <param name="FIDCode">"-1为默认播放，其他为实际的鞋子标签"</param>
        public TryShoes GetTryShoes(string FIDCode)
        {
            TryShoes result = null;
            DataTable dt = null;
            try
            {
                //获取鞋子素材
                dt = fBusDisplayClient.GetPlayResult(FIDCode, ref fError);
                if (dt != null)
                {
                    result = new TryShoes();
                    //组织鞋子类
                    result.RFID = FIDCode;
                    result.ShoesBaseInfoseq = Convert.ToInt32(dt.TableName);
                    result.starttimes = DateTime.Now;
                    result.endtimes = DateTime.Now;
                    result.PlayData = dt;
                }
                else//素材为空的时候
                {
                    //以下代码用于获取鞋子的序号 用于播放一张默认无素材的图片
                    dt = fBusDisplayClient.GetShoeSeqByRFIDCode(FIDCode, ref fError);
                    if (dt != null)
                    {
                        result = new TryShoes();
                        //组织鞋子类
                        result.RFID = FIDCode;
                        result.ShoesBaseInfoseq = Convert.ToInt32(dt.Rows[0]["shoesseq"].ToString());
                        result.starttimes = DateTime.Now;
                        result.endtimes = DateTime.Now;
                        DataTable dtPlay = new DataTable(dt.Rows[0]["shoesseq"].ToString());
                        DataColumn dc = new DataColumn("ShoesSEQ");
                        dc.DataType = System.Type.GetType("System.Int32");

                        DataColumn dc1 = new DataColumn("RelativeURL");
                        DataColumn dc2 = new DataColumn("name");

                        DataColumn dc3 = new DataColumn("Type");
                        dc3.DataType = System.Type.GetType("System.Single");

                        DataColumn dc4 = new DataColumn("flag");
                        dc4.DataType = System.Type.GetType("System.String");

                        DataColumn dc5 = new DataColumn("SpeedCount");
                        dc5.DataType = System.Type.GetType("System.Int32");

                        DataColumn dc6 = new DataColumn("ID");
                        dc6.DataType = System.Type.GetType("System.Int32");

                        dtPlay.Columns.Add(dc);
                        dtPlay.Columns.Add(dc1);
                        dtPlay.Columns.Add(dc2);
                        dtPlay.Columns.Add(dc3);
                        dtPlay.Columns.Add(dc4);
                        dtPlay.Columns.Add(dc5);
                        dtPlay.Columns.Add(dc6);
                        DataRow dr = dtPlay.NewRow();
                        dr["ShoesSEQ"] = Convert.ToInt32(dt.Rows[0]["shoesseq"].ToString());
                        dr["RelativeURL"] = "\\Images\\DisplayClient\\";
                        dr["name"] = "noPic.jpg";
                        dr["Type"] = 0;
                        dr["flag"] = "8";
                        dr["SpeedCount"] = 1;
                        dr["ID"] = Convert.ToInt32(dt.Rows[0]["ID"].ToString());
                        dtPlay.Rows.Add(dr);
                        result.PlayData = dtPlay;
                    }
                    else
                        return result;
                }
            }
            catch
            {
                result = null;
            }
            return result;
        }


        public void FreeAll()
        {
            if (fMonitorTimer != null)
            {
                fMonitorTimer.Enabled = false;
                fMonitorTimer.Stop();
            }
          
        }
    }
}
