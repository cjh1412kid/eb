using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MemoryShare;
using System.Data;

namespace NuiteThousandsShoes
{
    public class PlayList
    {

        #region 私有变量的定义
        private object playObject = new object();
        private object loseObject = new object();
        //播放队列
        private List<TryShoes> fShoesPlayList = new List<TryShoes>();

        public List<TryShoes> ShoesPlayList
        {
            get { return fShoesPlayList; }
            //set { fShoesPlayList = value; }
        }
        //过期队列
        private List<TryShoes> fShoesLoseList = new List<TryShoes>();

        public List<TryShoes> ShoesLoseList
        {
            get { return fShoesLoseList; }
            //set { fShoesLoseList = value; }
        }
        #endregion

        #region 添加请求队列
        /// <summary>
        /// 添加用户请求队列
        /// </summary>
        /// <param name="sortuser"></param>
        public void Insert(TryShoes fTryShoes)
        {
            lock (playObject)
            {
                try
                {
                    this.fShoesPlayList.Add(fTryShoes);

                }
                catch
                {
                   // throw;
                }
            }
        }
        #endregion

        #region 删除请求队列
        /// <summary>
        /// 删除请求队列
        /// </summary>
        public void RemoveBy(TryShoes fTryShoes)
        {
            lock (playObject)
            {
                try
                {
                    this.fShoesPlayList.Remove(fTryShoes);
                }
                catch
                { }
            }
        }
        #endregion


        /// <summary>
        /// 更新时间
        /// </summary>
        /// <param name="RFID"></param>
        /// <param name="time"></param>
        public void UpdateTime(string RFID, string time)
        {
            lock (playObject)
            {
                try
                {
                    fShoesPlayList.Find((TryShoes t) => t.RFID == RFID).endtimes = Convert.ToDateTime(time);
                }
                catch
                { }
            }
        }


        /// <summary>
        /// 判断标签是否在队列中
        /// </summary>
        /// <param name="FRID"></param>
        /// <returns></returns>
        public bool IsContaint(string FRID)
        {
            bool result = false;
            try
            {
                result = fShoesPlayList.Exists((TryShoes t) => t.RFID == FRID ? true : false);
            }
            catch
            {
                result = false;
            }
            return result;
        }

        public bool SetToShareData()
        {
            InitConst.MemoryShareMappedForOut.SetData("BShoeListRefresh", "1");
            DataTable dt = new DataTable();
            if (fShoesPlayList == null) return false;
            foreach (TryShoes tr in fShoesPlayList)
            {
                if (tr == null) continue;
                if (tr.PlayData == null) continue;
                if (dt.Rows.Count == 0)
                {
                    dt = tr.PlayData.Clone();
                }
                dt.Merge(tr.PlayData);
            }
            bool bsuc = InitConst.MemoryShareMappedForOut.SetData("ShoeList", dt);
            InitConst.MemoryShareMappedForOut.SetData("BShoeListRefresh", "0");
            if (dt.Rows.Count == 0) return false;
            return true;
        }
        public DataTable GetShareData()
        {
            object obj = InitConst.MemoryShareMappedForOut.GetData("BShoeListRefresh");
            while (obj!=null &&obj.ToString() != "0")
            {
                obj = InitConst.MemoryShareMappedForOut.GetData("BShoeListRefresh");
                System.Threading.Thread.Sleep(100);
            }
            obj = InitConst.MemoryShareMappedForOut.GetData("ShoeList");
            if (obj == null) return null;
            return obj as DataTable;
        }
        //***********************************************************过期队列相关操作****************************************************************

        /// <summary>
        /// 添加用户请求队列
        /// </summary>
        /// <param name="sortuser"></param>
        public void InsertLose(TryShoes fTryShoes)
        {
            lock (loseObject)
            {
                try
                {
                    this.fShoesLoseList.Add(fTryShoes);

                }
                catch
                {
                   // throw;
                }
            }
        }

        /// <summary>
        /// 删除请求队列
        /// </summary>
        public void RemoveLoseBy(TryShoes fTryShoes)
        {
            lock (loseObject)
            {
                try
                {
                    if (fTryShoes == null)
                    {
                        this.fShoesLoseList.Clear();
                    }
                    else
                    {
                        this.fShoesLoseList.Remove(fTryShoes);
                    }
                }
                catch
                { }
            }
        }
        public void SaveLoseListToFile()
        {
            string errorStr = "";
            if (fShoesLoseList.Count > 0)
            {
                BusiStatistics bs = new BusiStatistics();
                foreach (TryShoes trs in fShoesLoseList)
                {
                    bs.SaveStatisticsFile(trs, InitConst.ShopID, ref errorStr);
                }
                bs.WriteToFile(InitConst.ShopID);
                fShoesLoseList.Clear();
            }
        }
        //*******************************************************************************************************************************************************

    }
}
