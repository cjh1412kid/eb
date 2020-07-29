using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO.Compression;
using Pacia.Server.Common;
using System.Threading;
using System.Runtime.Serialization;
using System.Data;

namespace Nuite.DBAccessServer
{
    public partial class DBAccessServerRemoting : MarshalByRefObject
    {

        public string IsLocalShopExist(string userName, string pwd)
        {
            ServiceResultData serviceResultData = null;
            BusiInitProcedure busi = new BusiInitProcedure(ref serviceResultData);
            try
            {
                busi.IsLocalShopExist(userName, pwd);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }
        public string IsLocalBrandExist(string userName, string pwd)
        {
            ServiceResultData serviceResultData = null;
            BusiInitProcedure busi = new BusiInitProcedure(ref serviceResultData);
            try
            {
                busi.IsLocalBrandExist(userName, pwd);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }

      
        public string SetLocalDataInfo(string userName, string pwd, List<BrandInfor> listBrand, ShopInfo shopInfo, ComplanyInfo complanyInfo)
        {
            ServiceResultData serviceResultData = null;
            BusiInitProcedure busi = new BusiInitProcedure(ref serviceResultData);
            try
            {
                busi.SetLocalDataInfo(userName, pwd, listBrand, shopInfo, complanyInfo);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }

        public string GetShopInfo(string userName, string pwd)
        {
            ServiceResultData serviceResultData = null;
            BusiInitProcedure busi = new BusiInitProcedure(ref serviceResultData);
            try
            {
                busi.GetShopInfo(userName, pwd);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }
    }
}
