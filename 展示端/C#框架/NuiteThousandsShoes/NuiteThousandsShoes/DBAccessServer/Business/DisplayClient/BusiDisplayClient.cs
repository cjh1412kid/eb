using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Pacia.Server.Common;
using System.Data;
using System.Windows.Forms;

namespace Nuite.DBAccessServer
{
    public class BusiDisplayClient : BusiKernel
    {
        private static int fCntForSeq = 0;//对数据库记录数据序号唯一值的使用
        public BusiDisplayClient(ref ServiceResultData serviceResultObject)
        {
            serviceResultObject = this.ServiceResultData;
        }

        #region 硬件配置信息
        /// <summary>
        /// 获取硬件配置信息
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        public void GetHardInfoResult(string userName, string pwd)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                string sqlStr = "select * from NWBASE_HARDWAREPARAMETERSET where Del=0 or del is null";


                if (this.OpenConnection())
                {

                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (!ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {                       
                        this.ServiceResultData.DataTable = dtData;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetHardInfoResult异常：" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }

        #endregion 


        #region 获取素材相关
        /// <summary>
        /// 根据标签号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="RFIDCode">-1时表示获取默认宣传素材</param>
        public void GetPlayResult(string userName, string pwd, string RFIDCode)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                string sqlStr = "";
                switch (RFIDCode)
                {
                    case "-1"://默认播放视频
                        sqlStr = string.Format(" select * from NWProduct_DefaultPlayList d where [Select] =1 and [Flag]=1 and (Del=0 or del is null) ");
                        break;
                    case "-2"://默认播放背景音频
                        sqlStr = string.Format(" select * from NWProduct_DefaultPlayList d where [Select] =1 and [Flag]=0 and (Del=0 or del is null) ");
                        break;
                    default :
                        //标签—鞋子—波茨—品牌—公司
                        //鞋子—素材
                        //鞋子—播放模式
                        sqlStr = "select a.ShoesSEQ,a.RelativeURL,a.MaterialFileName as name,a.MaterialType as Type,d.flag,d.SpeedCount from NWBase_MaterialBaseInfo a,";
                        sqlStr += "	NWBase_ShoesRFIDCodeRelation b,NWProduct_ShoesPlayModel c,NWProduct_DefaultPlayModel d";
                        sqlStr += " where a.ShoesSEQ=b.SHOESSEQ";
                        sqlStr += " and a.ShoesSEQ=c.shoeBaseInfo_seq";
                        sqlStr += " and c.DefaultPlayModel_SEQ=d.seq";
                        sqlStr += "  and (a.Del=0 or a.del is null) ";
                        sqlStr += "  and (b.Del=0 or b.del is null) ";
                        sqlStr += "  and (c.Del=0 or c.del is null) ";
                        sqlStr += "  and (d.Del=0 or d.del is null) ";
                        sqlStr += "  and b.RFIDCode='" + RFIDCode + "'";
                        break;
                }

                if (this.OpenConnection())
                {

                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (!ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {
                        dtData.TableName = RFIDCode;
                        if (RFIDCode != "-1" && RFIDCode != "-2")
                        {
                            dtData.TableName = dtData.Rows[0]["ShoesSEQ"].ToString();
                            //dtData.Columns.Remove("ShoesSEQ");
                            dtData.Columns.Add("ID", typeof(int));
                            string flag = dtData.Rows[0]["flag"].ToString();
                            string[] flagAtrr = flag.Split(',');
                            for (int i = 0; i < dtData.Rows.Count; i++)
                            {
                                fCntForSeq++;
                                if (fCntForSeq > int.MaxValue - 3)
                                {
                                    fCntForSeq = 1;
                                }
                                DataRow dr = dtData.Rows[i];
                                dr["ID"] = fCntForSeq;
                                if (i < flagAtrr.Length)
                                {
                                    dr["flag"] = flagAtrr[i];
                                }
                                else
                                {
                                    dr["flag"] = flagAtrr[0];
                                }
                            }
                        }
                        this.ServiceResultData.DataTable = dtData;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetPlayResult异常：" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }

        /// <summary>
        /// 根据鞋子序号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoeSeq">鞋子序号</param>
        public void GetPlayResultByShoeSeq(string userName, string pwd, string shoeSeq)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                string sqlStr = "";

                sqlStr = "select a.ShoesSEQ,a.RelativeURL,a.MaterialFileName as name,a.MaterialType as Type from NWBase_MaterialBaseInfo a where a.shoesseq="+shoeSeq+" and (a.Del=0 or a.del is null)";

                if (this.OpenConnection())
                {

                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (!ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {
                        this.ServiceResultData.DataTable = dtData;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetPlayResult异常：" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }



        /// <summary>
        /// 根据鞋子货号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoeId">鞋子货号</param>
        public void GetPlayResultByShoeID(string userName, string pwd, string shoeId)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                string sqlStr = "";

                //sqlStr = "select a.ShoesSEQ,a.RelativeURL,a.MaterialFileName as name,a.MaterialType as Type from NWBase_MaterialBaseInfo a where a.shoesseq=" + shoeSeq + " and (a.Del=0 or a.del is null)";
                sqlStr = "select a.ShoesSEQ,a.RelativeURL,a.MaterialFileName as name,a.MaterialType as Type from NWBase_MaterialBaseInfo a ,NWBASE_SHOESBASEINFO s where s.GOODID = '"+shoeId+"' and a.shoesseq = s.SEQ and(a.Del = 0 or a.del is null)";

                if (this.OpenConnection())
                {

                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (!ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {
                        this.ServiceResultData.DataTable = dtData;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetPlayResult异常：" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }

        #endregion


        #region 根据RFID获取鞋子序号
        /// <summary>
        /// 根据RFID获取鞋子序号
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="RFIDCode">RFIDCode</param>
        public void GetShoeSeqByRFIDCode(string userName, string pwd, string RFIDCode)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                string sqlStr = "select shoesseq from NWBase_ShoesRFIDCodeRelation where RFIDCode='" + RFIDCode + "' and  Del=0 or del is null";
                if (this.OpenConnection())
                {
                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (!ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {
                        dtData.TableName = RFIDCode;
                        if (RFIDCode != "-1" && RFIDCode != "-2")
                        {
                            dtData.TableName = dtData.Rows[0]["shoesseq"].ToString();
                            dtData.Columns.Add("ID", typeof(int));
                                fCntForSeq++;
                                if (fCntForSeq > int.MaxValue - 3)
                                {
                                    fCntForSeq = 1;
                                }
                                DataRow dr = dtData.Rows[0];
                                dr["ID"] = fCntForSeq;
                        }
                        this.ServiceResultData.DataTable = dtData;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetShoeSeqByRFIDCode异常：" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }

        #endregion 

    }
}
