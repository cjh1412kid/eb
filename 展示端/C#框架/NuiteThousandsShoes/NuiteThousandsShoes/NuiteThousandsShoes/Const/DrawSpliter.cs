using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Windows.Forms;
namespace NuiteThousandsShoes
{
    public class DrawSpliter
    {
        /// <summary>
        /// 给窗体添加一个十字架
        /// </summary>
        /// <param name="formObj"></param>
        public static void DrowSpliterObj(Form formObj)
        {
            int spliterWidth = 16;
            string configURL = Application.StartupPath + @"/Config/Config.xml";
            XmlConfig xc = new XmlConfig(configURL);
            XmlData xd = xc.GetXmlNodeContentByParentNode("DrawSpliter", "BDraw");
            if (xd != null)
            {
                if (int.Parse(xd.NodeValue) == 0)//不绘制
                    return;
            }
            else
                return;//不绘制
            xd = xc.GetXmlNodeContentByParentNode("DrawSpliter", "Width");
            if (xd != null)
            {
                spliterWidth = int.Parse(xd.NodeValue);
            }else
                spliterWidth = 16;
            
            int width;
            int height;
            Rectangle ScreenArea = Screen.GetBounds(formObj);
            width = ScreenArea.Width; //屏幕宽度 int height1 = ScreenArea.Height; //屏幕高度
            height = ScreenArea.Height;
            PictureBox pbH = new PictureBox();
            pbH.BackgroundImage = Image.FromFile(Application.StartupPath + @"\Images\DisplayClient\" + "black.png");
            pbH.Width = width;
            pbH.Height = spliterWidth;
            pbH.Location = new Point(0, height / 2 - spliterWidth / 2);
            pbH.BackgroundImageLayout = ImageLayout.Stretch;
            formObj.Controls.Add(pbH);
            pbH.BringToFront();

            PictureBox pbV = new PictureBox();
            pbV.BackgroundImage = Image.FromFile(Application.StartupPath + @"\Images\DisplayClient\" + "black.png");
            pbV.Width = spliterWidth;
            pbV.Height = height;
            pbV.Location = new Point(width / 2 - spliterWidth / 2, 0);
            pbV.BackgroundImageLayout = ImageLayout.Stretch;
            formObj.Controls.Add(pbV);
            pbV.BringToFront();
        }



        //public static void DrowSpliterObj(Form formObj)
        //{
        //    int spliterWidth = 16;
        //    string configURL = Application.StartupPath + @"/Config/Config.xml";
        //    XmlConfig xc = new XmlConfig(configURL);
        //    XmlData xd = xc.GetXmlNodeContentByParentNode("DrawSpliter", "BDraw");
        //    if (xd != null)
        //    {
        //        if (int.Parse(xd.NodeValue) == 0)//不绘制
        //            return;
        //    }
        //    else
        //        return;//不绘制
        //    xd = xc.GetXmlNodeContentByParentNode("DrawSpliter", "Width");
        //    if (xd != null)
        //    {
        //        spliterWidth = int.Parse(xd.NodeValue);
        //    }
        //    else
        //        spliterWidth = 16;

        //    int width;
        //    int height;
        //    Rectangle ScreenArea = Screen.GetBounds(formObj);
        //    width = ScreenArea.Width; //屏幕宽度 int height1 = ScreenArea.Height; //屏幕高度
        //    height = ScreenArea.Height;
        //    PictureBox pbH = new PictureBox();
        //    pbH.BackgroundImage = Image.FromFile(Application.StartupPath + @"\Images\DisplayClient\" + "blackH.png");
        //    pbH.Width = width;
        //    pbH.Height = spliterWidth;
        //    pbH.Location = new Point(0, height / 2 - spliterWidth / 2);
        //    pbH.BackgroundImageLayout = ImageLayout.Stretch;
        //    formObj.Controls.Add(pbH);
        //    pbH.BringToFront();


        //    float cutH = (float)spliterWidth / 20;
        //    if (cutH > 0 && cutH < 1)
        //        cutH = 1;
        //    else
        //        cutH = spliterWidth / 20;

        //    PictureBox pbV = new PictureBox();
        //    pbV.BackgroundImage = Image.FromFile(Application.StartupPath + @"\Images\DisplayClient\" + "blackV.png");

        //    pbV.Width = spliterWidth;
        //    pbV.Height = height / 2 - int.Parse(cutH.ToString());
        //    pbV.Location = new Point(width / 2 - spliterWidth / 2, 0);
        //    pbV.BackgroundImageLayout = ImageLayout.Stretch;
        //    formObj.Controls.Add(pbV);
        //    pbV.BringToFront();

        //    PictureBox pbV1 = new PictureBox();
        //    pbV1.BackgroundImage = Image.FromFile(Application.StartupPath + @"\Images\DisplayClient\" + "blackV.png");
        //    pbV1.Width = spliterWidth;
        //    pbV1.Height = height / 2 - int.Parse(cutH.ToString());
        //    pbV1.Location = new Point(width / 2 - spliterWidth / 2, height / 2 + int.Parse(cutH.ToString()));
        //    pbV1.BackgroundImageLayout = ImageLayout.Stretch;
        //    formObj.Controls.Add(pbV1);
        //    pbV1.BringToFront();
        //}
    }
}
