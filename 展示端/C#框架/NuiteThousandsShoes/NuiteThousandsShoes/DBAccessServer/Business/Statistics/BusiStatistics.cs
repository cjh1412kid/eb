using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Pacia.Server.Common;
using System.Data;

namespace Nuite.DBAccessServer
{
    public class BusiStatistics : BusiKernel
    {
        public BusiStatistics(ref ServiceResultData serviceResultObject)
        {
            serviceResultObject = this.ServiceResultData;
        }



        #region 统计信息查询
        /// <summary>
        /// 获取统计信息表
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="time"></param>
        /// <param name="flag"></param>
        public void GetStatisticsResult(string userName, string pwd, string time, int flag)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                time = Convert.ToDateTime(time).AddDays(flag).ToString("yyyy/MM/dd");
                string curMonth=Convert.ToDateTime(time).AddMonths(flag).Month.ToString();
                string curYeat=Convert.ToDateTime(time).AddMonths(flag).Year.ToString();
                //统计信息查询
                //string sqlStr = string.Format("select s.TwoDimensionCode as 鞋号, p.TryCount as 试穿数量,p.TryTimes as 试穿时间, p.flow as 网站浏览量,p.sales as 销量,day(p.datatime) as 时间 from 	NWProduct_Productstatistics p left join NWBase_ShoesBaseInfo s on p.FK_ShoesBaseInfo_seq=s.seq where month(p.datatime)={0} and year(p.datatime)={1}  order by p.datatime", curMonth, curYeat);
                string sqlStr = string.Format("select p.TwoDimensionCode as 鞋号, s.TryCount as 试穿数量,s.TryTimes as 试穿时间, s.flow as 网站浏览量,s.sales as 销量,s.datatime as 时间 from NWBase_ShoesBaseInfo p left join NWProduct_Productstatistics s on (s.FK_ShoesBaseInfo_seq=p.seq  and s.DataTime=#{0}#) where 1=1 order by p.TwoDimensionCode", time);
                this.connectionStringIndex = 0;//基础库
                if (this.OpenConnection())
                {

                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (!ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {
                        for (int i = 0; i < dtData.Columns.Count; i++)
                        {
                            switch (dtData.Columns[i].ColumnName)
                            {
                                case "试穿数量":
                                    dtData.Columns[i].ColumnName += "/次";
                                    break;
                                case "试穿时间":
                                    dtData.Columns[i].ColumnName += "/秒";
                                    break;
                                case "网站浏览量":
                                    dtData.Columns[i].ColumnName += "/次";
                                    break;
                                case "销量":
                                    dtData.Columns[i].ColumnName += "/双";
                                    break;
                                case "时间":
                                    dtData.Columns[i].ColumnName += "/日";
                                    break;
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
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetStatisticsResult异常：" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }
        #endregion
        

        #region 统计信息保存
        /// <summary>
        /// 修改（插入、更新）试鞋的信息
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="rfid"></param>
        public void SaveStatisticsResult(string userName, string pwd, string shoesbaseseq, int count, double times)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    return;
                }
                string DataTime = DateTime.Now.ToString("yyyy/MM/dd");
                //查询当天统计表内是否存在试穿信息
                string sqlStr = string.Format("select * from NWProduct_Productstatistics p where p.DataTime=#{0}# and p.FK_ShoesBaseInfo_seq={1}", DataTime, shoesbaseseq);
                this.connectionStringIndex = 0;//基础库
                if (this.OpenConnection())
                {    
                    DataTable dtData = this.ExecuteQuery(sqlStr);
                    if (dtData != null && dtData.Rows.Count > 0)
                    {
                        //更新当前字段的信息
                        sqlStr = "update NWProduct_Productstatistics set ";
                        sqlStr += string.Format(" TryCount=TryCount+{0},TryTimes=TryTimes+{1},InputTime='{2}'", count, times, DateTime.Now.ToString());//序号sql
                        sqlStr += string.Format(" where FK_ShoesBaseInfo_seq={0} and DataTime=#{1}#", shoesbaseseq, DataTime);
                    }
                    else
                    {
                        //插入当前字段的信息
                        sqlStr = "INSERT INTO  NWProduct_Productstatistics(FK_ShoesBaseInfo_seq,TryCount,TryTimes,DataTime,InputTime)";
                        sqlStr += string.Format("  VALUES ({0},{1},{2},'{3}','{4}')", shoesbaseseq, count, times, DataTime, DateTime.Now);

                    }
                    this.StartTransaction();
                    int value = this.ExecuteUpdate(sqlStr); 
                    if (value<1)
                    {
                        this.ServiceResultData.BusinessResult = 1004;//查询数据库列表为空!
                    }
                    else
                    {
                        this.CommitTransaction();
                        this.ServiceResultData.BusinessResult = 0;
                    }
                    this.StopTransaction();
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
            }
            catch (Exception ex)
            {
                this.RollbackTransaction();
                this.StopTransaction();
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "SaveStatisticsResult异常：" + ex.Message;
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
