using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NuiteThousandsShoes
{
    /// <summary>
    /// 硬件设备信息
    /// </summary>
    public class HardInfo
    {
        private int shopSeq;
        /// <summary>
        /// 设备所在店铺序号
        /// </summary>
        public int ShopSeq
        {
            get { return shopSeq; }
            set { shopSeq = value; }
        }

        private string hardwareID;
        /// <summary>
        /// 硬件编号
        /// </summary>
        public string HardwareID
        {
            get { return hardwareID; }
            set { hardwareID = value; }
        }

        private string hardwareName;
        /// <summary>
        /// 硬件名称
        /// </summary>
        public string HardwareName
        {
            get { return hardwareName; }
            set { hardwareName = value; }
        }

        private string useAntennaPort;
        /// <summary>
        /// 天线口标识
        /// </summary>
        public string UseAntennaPort
        {
            get { return useAntennaPort; }
            set { useAntennaPort = value; }
        }

        private int readerType;
        /// <summary>
        /// 读取类型（0，1）
        /// </summary>
        public int ReaderType
        {
            get { return readerType; }
            set { readerType = value; }
        }

        private string ip;
        /// <summary>
        /// IP
        /// </summary>
        public string IP
        {
            get { return ip; }
            set { ip = value; }
        }

        private string port;
        /// <summary>
        /// 端口
        /// </summary>
        public string Port
        {
            get { return port; }
            set { port = value; }
        }

        private int outPutPower;
        /// <summary>
        /// 射频功率
        /// </summary>
        public int OutPutPower
        {
            get { return outPutPower; }
            set { outPutPower = value; }
        }

        private int rFIDEffectTime;
        /// <summary>
        /// 有效时长（秒）
        /// </summary>
        public int RFIDEffectTime
        {
            get { return rFIDEffectTime; }
            set { rFIDEffectTime = value; }
        }


        private int minimumfrequency;
        /// <summary>
        /// 频率最小值
        /// </summary>
        public int Minimumfrequency
        {
            get { return minimumfrequency; }
            set { minimumfrequency = value; }
        }

        private int maxmumfrequency;
        /// <summary>
        /// 频率最大值
        /// </summary>
        public int Maxmumfrequency
        {
            get { return maxmumfrequency; }
            set { maxmumfrequency = value; }
        }
        private float frequencyspace;
        /// <summary>
        /// 频率间隔
        /// </summary>
        public float Frequencyspace
        {
            get { return frequencyspace; }
            set { frequencyspace = value; }
        }

        private int bleepermode;
        /// <summary>
        /// 蜂鸣设置（0，1，2，3）
        /// </summary>
        public int Bleepermode
        {
            get { return bleepermode; }
            set { bleepermode = value; }
        }

        private int proFileMode;
        /// <summary>
        /// 射频通讯链路（0，1，2，3，4）
        /// </summary>
        public int ProFileMode
        {
            get { return proFileMode; }
            set { proFileMode = value; }
        }

        private int returnLoss;
        /// <summary>
        /// 回波损耗阀值
        /// </summary>
        public int ReturnLoss
        {
            get { return returnLoss; }
            set { returnLoss = value; }
        }


    }
}
