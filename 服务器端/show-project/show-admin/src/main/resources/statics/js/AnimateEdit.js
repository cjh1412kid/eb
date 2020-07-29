/*用户角色*/
var userRoleSeq;

/*无权限判断*/
function notEditPermission(node) {
  //总部设定，且用户角色全国以下，视为无操作权限
  if (node && node.belongTo == 2 && userRoleSeq && userRoleSeq > 1) {
    return true;
  } else {
    return false;
  }
}

/**模板树*/
var zTree1, zTree2, zTree3,
  $Menu1,
  $Menu2,
  $Tree1 = $("#tree1"),
  $Tree2 = $("#tree2"),
  $Tree3 = $("#tree3");
/*素材路径*/
var materialImagePath = "/picture/shop-model/";

function getHostPath() {
  return window.location.protocol + "//" + window.location.host;
}

function getProjectPath() {
  return window.location.protocol + "//" + window.location.host + "/show-admin";
}

var currRClickTreeNode, templateTreeNode, currTreeId, clickedNode;
var nodeNameChangeFlag = true, prevNodeName;
var setting = {
  view: {
    addDiyDom: addDiyDom,
    selectedMulti: false,
    dblClickExpand: function (treeId, treeNode) {
      return treeNode.level > 0;
    }
  },
  edit: {
    enable: true,
    editNameSelectAll: true,
    removeTitle: "删除",
    renameTitle: "重命名"
  }
  ,
  data: {
    keep: {
      leaf: true,
      parent:
        true
    }
    ,
    simpleData: {
      enable: true,
      idKey:
        "id",
      pIdKey:
        "pId",
      rootPId:
        0
    }
  }
  ,
  callback: {
    onRightClick: function (event, treeId, treeNode) {
      //权限控制
      if (notEditPermission(treeNode)) return;
      var treeObj = $.fn.zTree.getZTreeObj(treeId);
      treeObj.selectNode(treeNode);
      currTreeId = treeId;
      rMenuContext.openMenu($Menu1, event);
      currRClickTreeNode = treeNode;
    },
    beforeRemove: function (treeId, treeNode) {
      //根目录不可删除
      if (treeNode.id == 0) return false;

      if (notEditPermission(treeNode)) return false;

      var treeObj = $.fn.zTree.getZTreeObj(treeId);
      //获取选中节点包括所有子节点的集合
      var ids = [];
      ids = getAllChildrenNodes(ids, treeNode);

      layer.confirm('您真的要删除模版[ ' + treeNode.name + ' ]吗?', {icon: 3, title: '询问'}, function (index) {

        $.ajax({
          type: "POST",
          url: getProjectPath() + "/sys/festivalTemplate/delAll",
          dataType: "json",
          contentType: "application/json;charset=utf-8",
          data: JSON.stringify(ids),
          success: function (data) {
            if (data.code == 1) {
              treeObj.removeNode(treeNode);
              layer.msg("删除模版成功", {icon: 6});
            } else {
              layer.alert("删除模版[ " + treeNode.name + " ]失败，请重试或联系管理员", {icon: 0});
            }
          }
        })

        layer.close(index);
      });

      return false;
    },
    beforeEditName: function zTreeBeforeEditName(treeId, treeNode) {
      if (notEditPermission(treeNode)) return false;
      //根目录不可编辑
      return treeNode.id > 0;
    },
    beforeRename: function (treeId, treeNode, newName, isCancel) {
      //新名称不能为空
      if (newName && newName.trim().length > 0) {
        if (newName.trim() == treeNode.name) {
          nodeNameChangeFlag = false;
        } else {
          prevNodeName = treeNode.name;
          nodeNameChangeFlag = true;
        }
        return true
      } else {
        layer.msg('名称不能为空或空格', {icon: 2});
        return false
      }
    },
    onRename: function (event, treeId, treeNode, isCancel) {
      var treeObj = $.fn.zTree.getZTreeObj(treeId);
      if (nodeNameChangeFlag) {
        //节点名称发生改变，修改数据库
        treeNode.name = treeNode.name.trim();
        treeObj.updateNode(treeNode);
        $.post(getProjectPath() + "/sys/festivalTemplate/update", {
          seq: treeNode.id,
          name: treeNode.name
        }, function (data) {
          if (data.code == 1) {
            layer.msg("模版名称修改成功！", {icon: 6})
          } else {
            layer.msg(data.msg, {icon: 0});
            //后台修改发生异常，节点名称还原
            treeNode.name = prevNodeName;
            treeObj.updateNode(treeNode);
          }
        })
      } else {
        //名称相同，不修改数据库
        treeNode.name = treeNode.name.trim();
        treeObj.updateNode(treeNode);
      }
    },
    onClick: function (event, treeId, treeNode) {
      //动画设置界面没关闭
      if (vm.animateShow) return;

      //节点聚焦处理
      if (clickedNode) {
        //如果当前点击节点与上一次相同
        if (treeNode.id == clickedNode.id) {
          return;
        }
        var tid = clickedNode.tId;
        var prevTreeId = tid.substring(0, tid.indexOf("_"));
        //前后两次单击的节点不是同一个树
        if (prevTreeId != treeId) {
          var prevTreeObj = $.fn.zTree.getZTreeObj(prevTreeId);
          prevTreeObj.cancelSelectedNode(clickedNode);
        }
      }
      clickedNode = treeNode;

      //判断节点类型是否是模版文件
      if (treeNode.nodeType == 1) {
        //判断当前编辑模版是否处于编辑状态
        /* if ($("#edit_content").hasClass("edit_ing")) {
           layer.confirm('当前有模版正在编辑，确定放弃吗?', {icon: 3, title: '提示'}, function (index) {
             templateTreeNode = treeNode;
             showEditTemplate(treeNode);
             autoCheckShopNodeAndValidTime(treeNode);
             layer.close(index);
           }, function (index) {
             if (templateTreeNode) {
               templateTreeUtil.focusShowTemplateNode();
               clickedNode = templateTreeNode;
               var treeObj = $.fn.zTree.getZTreeObj(treeId);
               treeObj.cancelSelectedNode(treeNode);
             }

           });
         } else {*/
        //节点禁用，则只读，不设编辑标记
        /*    if (!notEditPermission(treeNode)) {
              $("#edit_content").addClass("edit_ing")
            }*/
        templateTreeNode = treeNode;
        showEditTemplate(treeNode);
        templateTreeUtil.queryTemplateControl(treeNode);
        // }

      } else {
        layer.msg('不是模版文件', {icon: 2, time: 1000});
      }

    }
  }
};

// 递归，获取所有子节点
function getAllChildrenNodes(ids, treeNode) {
  ids.push(treeNode.id);

  if (treeNode.isParent) {
    for (var i in treeNode.children) {
      getAllChildrenNodes(ids, treeNode.children[i]);
    }
  }
  return ids;
}

function addDiyDom(treeId, treeNode) {
  var aObj = $("#" + treeNode.tId + "_a");
  if (treeNode.validFlag == 1) {
    var editStr = '&nbsp;<span class="label label-danger">&nbsp;生效中&nbsp;</span>';
    aObj.append(editStr);
  } else if (treeNode.validFlag == 2) {
    var editStr = '&nbsp;<span class="label label-info">&nbsp;待生效&nbsp;</span>';
    aObj.append(editStr);
  }
}

function autoCheckShopNodeAndValidTime(treeNode) {
  //2 总部模版 1我的模版
  /*  if (treeNode.belongTo == 2) {
      zTree3.checkAllNodes(true);
      //禁用树
      /!* var nodes = zTree3.getNodes();
       for (var i = 0, l = nodes.length; i < l; i++) {
         zTree3.setChkDisabled(nodes[i], true, true, true);
       }*!/
    } else {
      //取消禁用
      /!* var nodes = zTree3.getNodes();
       for (var i = 0, l = nodes.length; i < l; i++) {
         zTree3.setChkDisabled(nodes[i], false, true, true);
       }*!/
      //门店用户
      /!* if (userRoleSeq && userRoleSeq == 4) {
         zTree3.checkAllNodes(true);

       } else {
         //取消所有节点勾选,自定义勾选
         zTree3.checkAllNodes(false);
       }*!/
      //根据用户的设置记录，自动勾选门店和设置有效时间

    }*/

}

var shopSelectSetting = {
  view: {
    selectedMulti: false,
  },
  check: {
    enable: true
  },
  data: {
    keep: {
      leaf: true,
      parent:
        true
    }
    ,
    simpleData: {
      enable: true,
      idKey:
        "id",
      pIdKey:
        "pId",
      rootPId:
        0
    }
  }
  ,
  callback: {}
};


/************右键菜单初始化***********************/
var menuObj = [
  {
    text: '添加分类', action: function (e) {
      templateTreeUtil.addNewNode(2);
    }
  },
  {
    text: '新建模版', action: function (e) {
      templateTreeUtil.addNewNode(1);
    }
  },
  {divider: true},
  {
    text: '删除应用设置', action: function (e) {
      templateTreeUtil.cancelUseTemplate();
    }
  }
]
rMenuContext.init({
  preventDoubleContext: true,
  compress: true
});
$Menu1 = rMenuContext.attach('#tree1', menuObj),
  $Menu2 = rMenuContext.attach('#tree2', menuObj);

var templateTreeUtil = {
  /*取消应用模板树*/
  cancelUseTemplate: function () {
    if (!currRClickTreeNode.id) return;
    var tempName = currRClickTreeNode.name;
    var belongTo = currRClickTreeNode.belongTo;
    $.post(getProjectPath() + "/sys/shopAnimationControl/del/" + currRClickTreeNode.id, function (data) {
      if (data.code == 1) {
        layer.msg("模版应用设置删除成功【" + tempName + "】", {icon: 1});
        extraVM.startTime = "";
        extraVM.endTime = "";
        extraVM.inputTime = "";
        if (belongTo == 2 || userRoleSeq == 4) {
          zTree3.checkAllNodes(true);
        } else {
          //清理所有勾选门店，及时间
          zTree3.checkAllNodes(false);
        }
        reloadTreeData();
      } else {
        layer.msg("模版应用设置删除失败，请重试", {icon: 0});
      }
    })
  },
  /*查询模版控制信息，包括有效时间，选择门店,模版背景。点击节时调用*/
  queryTemplateControl: function (treeNode) {
    if (!treeNode.id) return;

    //折叠门店全部节点
    zTree3.expandAll(false);

    //清除背景
    $("#screen").get(0).removeAttribute("style");

    //获取模版背景
    getTemplateBackground(treeNode.id);

    $.post(getProjectPath() + "/sys/shopAnimationControl/query/" + treeNode.id, function (data) {
      if (data.result) {
        extraVM.startTime = data.result.svalidDate;
        extraVM.endTime = data.result.evalidDate;
        extraVM.inputTime = data.result.inputTime;
        //总部模版
        /*        if (treeNode.belongTo == 2) {
                  zTree3.checkAllNodes(true);
                } else {*/
        //勾选门店
        var shopSeqs = data.result.shopSeqs;
        zTree3.checkAllNodes(false);
        var shopArr = shopSeqs.split(",");
        var node;

        for (var i in shopArr) {
          node = zTree3.getNodeByParam("id", "s" + shopArr[i]);
          if (node) {
            zTree3.checkNode(node, true, true, true);
          }
        }
        /*}*/

      } else {
        //模版应用设置不存在
        //总部模版，或门店用户，全勾选
        if (treeNode.belongTo == 2 || userRoleSeq == 4) {
          zTree3.checkAllNodes(true);
        } else {
          //清理所有勾选门店，及时间
          zTree3.checkAllNodes(false);
        }
        extraVM.startTime = "";
        extraVM.endTime = "";
        extraVM.inputTime = "";
      }
    })
  },
  focusShowTemplateNode: function () {
    if (templateTreeNode) {
      var tid = templateTreeNode.tId;
      var treeId = tid.substring(0, tid.indexOf("_"));
      var templateTreeObj = $.fn.zTree.getZTreeObj(treeId);
      templateTreeObj.selectNode(templateTreeNode);
    }
  },
  addNewNode: function (nodeType) {
    //模版节点不能添加子文件
    if (currRClickTreeNode.nodeType == 1) {
      return;
    }

    layer.prompt({
      formType: 0,
      value: '',
      title: nodeType == 2 ? '新建分类' : "新建模板",
    }, function (value, index, elem) {
      // console.log(value, elem);
      //判断名称是否为空
      //判断兄弟节点是否名称相同
      var nodeName;
      if (value && (nodeName = value.trim()).length > 0) {
        //名称合法
        $.post(getProjectPath() + "/sys/festivalTemplate/addNew", {
          name: nodeName,
          parentSeq: currRClickTreeNode.id,
          nodeType: nodeType,
          belongTo: currRClickTreeNode.belongTo
        }, function (data, status) {
          if (data.code == 1) {
            reloadTreeData()
          }
        })
        layer.close(index);
      }

    });
  }
}


/*********************模板树右键菜单配置 end**************************8*/


function reloadTreeData() {
  if (currTreeId == "tree1") {
    loadTree1();
  } else if (currTreeId == "tree2") {
    loadTree2();
  }
}


/**加载树*/
function loadTree1() {
  $.post(getProjectPath() + "/sys/festivalTemplate/selectCompanyTemplates", function (data, status) {
    var zNodes1 = [{id: 0, pId: -1, name: "总部模板", open: true, nodeType: 2, belongTo: 2}];
    if (data.result) {
      var item;
      for (var i in data.result) {
        item = data.result[i];
        zNodes1.push({
          id: item.seq,
          pId: item.parentSeq,
          name: item.name,
          nodeType: item.nodeType,
          validFlag: item.validFlag,
          open: true,
          belongTo: 2
        })
      }
    }
    $.fn.zTree.destroy("tree1");
    zTree1 = $.fn.zTree.init($Tree1, setting, zNodes1);
  })
}


function loadTree2() {

  $.post(getProjectPath() + "/sys/festivalTemplate/selectUserTemplates", function (data, status) {
    var zNodes2 = [{id: 0, pId: -1, name: "我的模板", open: true, nodeType: 2, belongTo: 1}];
    if (data.result) {
      var list = data.result;
      var item;
      for (var i in list) {
        item = list[i];
        zNodes2.push({
          id: item.seq,
          pId: item.parentSeq,
          name: item.name,
          nodeType: item.nodeType,
          validFlag: item.validFlag,
          open: true,
          belongTo: 1
        })
      }
    } else {
      // layer.alert("zTree2: 0", {icon: 5})
    }

    $.fn.zTree.destroy("tree2");
    zTree2 = $.fn.zTree.init($Tree2, setting, zNodes2);
  })
}

function loadShopSelectTree() {

  $.post(getProjectPath() + "/sys/shopTree/getShops", function (data, status) {
    var zNodes3 = [];
    if (data.result) {
      if (data.result) {
        var list = data.result;
        var item;
        for (var i in list) {
          item = list[i];
          zNodes3.push({
            id: item.id,
            pId: item.pid,
            seq: item.seq,
            name: item.nodeName,
            nodeType: item.shopFlag
          })
        }
      }

    }
    zTree3 = $.fn.zTree.init($Tree3, shopSelectSetting, zNodes3);
  })
}

/*获取所有被选商店节点的序号字符串*/
function getALlShopCheckedNodes() {
  var nodes = zTree3.getCheckedNodes(true);
  // console.log("nodes: ", nodes)
  var shopSeqs = [];
  var item;
  for (var i in nodes) {
    item = nodes[i];
    //门店标记，1门店，0区域
    if (item.nodeType) shopSeqs.push(item.seq);
  }

  return shopSeqs.join(",");
}

/******************加载素材图片********************/

/*创建图片元素对象*/
function createImageObj(materialObj, prefix) {
  var image = new Image();
  if (materialObj) {
    image.id = prefix + materialObj.seq;
    image.src = getHostPath() + materialImagePath + materialObj.path;
  }
  return image;
}

function loadUserMaterial() {
  $.post(getProjectPath() + "/sys/festival/material/fromUser", function (data, status) {
    if (data.result) {
      $("#self_material").empty();
      var material;
      for (var i in data.result) {
        material = data.result[i];
        $("#self_material").append(createImageObj(material, "wd"));
      }
    }
  })
}

function loadCompanyMaterial() {
  $.post(getProjectPath() + "/sys/festival/material/fromCompany", function (data, status) {
    if (data.result) {
      $("#center_material").empty();
      var material;
      for (var i in data.result) {
        material = data.result[i];
        $("#center_material").append(createImageObj(material, "zb"));
      }
    }
  })
}

/**
 * 初始化用户素材分页插件
 * @param pageSize 一页的数量
 */
function initUserMaterialPagination(pageSize) {
  pageSize = pageSize || 20;

  $.post(getProjectPath() + "/sys/festival/material/fromUser/" + pageSize + "/1", function (data, status) {
    // console.log("初始化用户素材分页", data.result)
    if (data.result) {
      //总页数
      var pages = data.result.pages;
      if (pages == 0) {
        $("#self_material").empty();
        return;
      }
      //初始化分页
      pagination("#self_material_page", pages, pageSize, loadUserMaterialByPage);
    }
  })
}


/**
 * 初始化总部素材分页插件
 * @param pageSize
 */
function initCompanyMaterialPagination(pageSize) {
  pageSize = pageSize || 20;
  $.post(getProjectPath() + "/sys/festival/material/fromCompany/" + pageSize + "/1", function (data, status) {
    // console.log("初始化总部素材分页", data.result)
    if (data.result) {
      //总页数
      var pages = data.result.pages;
      if (pages == 0) {
        $("#center_material").empty();
        return;
      }
      //初始化分页
      pagination("#center_material_page", pages, pageSize, loadCenterMaterialByPage);
    }
  })
}

/**
 * 按页查询用户素材
 * @param currPage
 * @param pageSize
 */
function loadUserMaterialByPage(currPage, pageSize) {
  $.post(getProjectPath() + "/sys/festival/material/fromUser/" + pageSize + "/" + currPage, function (data, status) {
    // console.log(data.result)
    if (data.result) {
      //总页数
      var records = data.result.records;
      var material;
      var $self_material = $("#self_material");
      $self_material.empty();
      for (var i in records) {
        material = records[i];
        $self_material.append(createImageObj(material, "wd"));
      }
    }
  })
}

/**
 * 按页查询总部素材
 * @param currPage
 * @param pageSize
 */
function loadCenterMaterialByPage(currPage, pageSize) {
  $.post(getProjectPath() + "/sys/festival/material/fromCompany/" + pageSize + "/" + currPage, function (data, status) {
    // console.log(data.result)
    if (data.result) {
      //总页数
      var records = data.result.records;
      var material;
      var $center_material = $("#center_material");
      $center_material.empty();
      for (var i in records) {
        material = records[i];
        $center_material.append(createImageObj(material, "zb"));
      }
    }
  })
}


/*************监听素材图片的单击和双击事件********************/
function listenMaterialImageClick() {
  $("#materialTabContent").on("click", "img", function (e) {
    // console.log("单击事件目标图片： ", this)

    if (!templateTreeNode) return;

    //背景设置判断
    if ($("#bg_add_btn").hasClass("bg-editing")) {
      //点击了背景设置按钮
      addNewTemplateBackground(this);
      return;
    }

    //判断是否打开了动画设置
    if (vm.animateShow) {
      var imageSrc = this.src;
      var lastIndex = imageSrc.lastIndexOf("_");
      if (lastIndex != -1) {
        var imageSize = imageSrc.substring(lastIndex + 1, imageSrc.lastIndexOf("."));
        if (imageSize && imageSize > 10 * 1024) {
          layer.msg("动画图片不得超过10KB", {icon: 0})
          return;
        }
      }

      var $animateMaterial = $(".m_a_active");
      if ($animateMaterial && $animateMaterial.length > 0) {
        $animateMaterial.empty().append(this.cloneNode());

        if ($animateMaterial[0].id == "am1") {
          vm.animate1.materialSeq = this.id.substring(2);
        } else if ($animateMaterial[0].id == "am2") {
          vm.animate2.materialSeq = this.id.substring(2);
        } else if ($animateMaterial[0].id == "am3") {
          vm.animate3.materialSeq = this.id.substring(2);
        } else if ($animateMaterial[0].id == "am4") {
          vm.animate4.materialSeq = this.id.substring(2);
        }
      } else {
        layer.msg("请先选中动画素材位置", {icon: 0});
      }
    } else {
      var $staticMaterial = $(".s_m_active");
      if ($staticMaterial && $staticMaterial.length > 0) {
        $staticMaterial.empty().append(this.cloneNode());
      } else {
        // layer.msg("请先选中静态素材位置", {icon: 0});
      }
    }
  })

  $("#materialTabContent").on("dblclick", "img", function (e) {
    if (userRoleSeq && userRoleSeq > 1) {
      var prefix = this.id.substring(0, 2);
      //门店权限不能删除总部素材
      if (prefix == "zb") return;
    }
    var materialSeq = this.id.substring(2);
    var $delMaterial = $(this);
    layer.confirm('是否删除此图片?', {icon: 3, title: '提示'}, function (index) {
      $.post(getProjectPath() + "/sys/festival/material/del?seq=" + materialSeq, function (data) {
        if (data.code == 1) {
          $delMaterial.remove();
          layer.msg("删除成功", {icon: 6});
        } else {
          layer.msg("该图片有模版占用，不能删除", {icon: 0});
        }
      })

      layer.close(index);
    });
  })

}

/****************添加素材图片按钮***************************/
var currMaterialTab = 1;

function uploadMaterial() {

  //查找当前标签页
  var $materialTab = $("#materialTab > .active")[0];
  currMaterialTab = $materialTab.id == "myTab" ? 1 : 2;
  if (currMaterialTab == 2 && userRoleSeq && userRoleSeq > 1) {
    layer.msg("您没有权限操作总部素材", {icon: 0, time: 1000});
    return;
  }

  $("#uploadImg").click();
}

/*******************监听图片上传***************************/
function listenImageUpload() {

  $("#uploadImg").change(function (e) {
    var files = this.files;
    if (!files || files.length == 0) return;

    var mTab = currMaterialTab;
    var formData = new FormData();
    for (var i = 0; i < files.length; i++) {

      if (files[i].type == "image/png") {
        if (files[i].size <= 1024 * 600) {
          formData.append("files", files[i]);
        } else {
          layer.msg("上传素材不得超过600 KB", {icon: 2});
          return;
        }
      } else {
        layer.msg("请上传png格式图片", {icon: 2});
        return;
      }
    }

    formData.append("belongTo", mTab);

    $.ajax({
      type: "POST",
      cache: false,
      url: getProjectPath() + "/sys/festival/material/upload",
      data: formData,
      dataType: 'json',
      contentType: false,
      processData: false,
      success: function (data) {
        if (data.code == 1) {
          // layer.msg('素材上传成功', {icon: 6});
          //刷新素材内容
          if (mTab == 1) {
            initUserMaterialPagination();
          } else {
            initCompanyMaterialPagination();
          }
        } else {
          layer.msg('素材上传失败', {icon: 5, time: 5000});
        }
      }
    })//ajax

  })//change

}


/*******************显示编辑模版*****************************/
function showEditTemplate(treeNode) {
  //显示模版
  vm.isShowEditTemplate = true;
  //清空模版数据
  vm.initEditTemplate();
  //获取模版数据，有则填充
  $.post(getProjectPath() + "/sys/festivalStaticContent/selectByTemplateSeq/" + treeNode.id, function (data) {

    if (data.result) {
      var staticMaterialContents = data.result;
      var styleName;
      var imageWrap;
      var sm;
      for (var i in staticMaterialContents) {
        sm = staticMaterialContents[i];
        styleName = "style" + sm.position;
        imageWrap = "#m" + sm.position;
        vm[styleName].width = sm.wper + "%";
        vm[styleName].height = sm.hper + "%";
        vm[styleName].opacity = sm.opacity / 10;
        //插入图片
        $(imageWrap).append(createImageObj(sm.material, "sc"));
      }
      $("#btn_s .btn").removeClass("disabled");
      if (notEditPermission(treeNode)) {
        extraVM.btnIsShow = false;
        vm.btnIsShow = false;
        vm.enterPreview();
      } else {
        extraVM.btnIsShow = true;
        vm.btnIsShow = true;
        vm.leavePreview();
      }
    }
  })

  layer.msg('打开模版【 ' + treeNode.name + ' 】', {icon: 6, time: 1000});
}

/*******************编辑动画设置*********************/
function listenBtns() {

  $("#animal_btn").click(function () {
    if (!templateTreeNode) {
      layer.msg('请打开模版再操作', {icon: 0, time: 1000});
      return;
    }
    //权限限制
    if (notEditPermission(templateTreeNode)) return;

    if ($(this).hasClass("disabled")) {
      return;
    } else {
      $("#btn_s .btn").addClass("disabled");
    }

    vm.initAnimatePanel();
    vm.animateShow = true;
    //获取当前模版的动画数据
    vm.getDataAfterOpenAnimate();

    layer.open({
      title: templateTreeNode.name || "动画设置",
      type: 1,
      area: ['640px', '460px'],
      content: $("#animate_edit"),
      btn: ['确定', '取消'],
      closeBtn: 1,
      shade: 0,
      move: false,
      resize: false,
      yes: function (index, layero) {
        vm.submitAnimateContent();
        vm.animateShow = false;
        $("#btn_s .btn").removeClass("disabled");
        layer.close(index);
      },
      btn2: function (index, layero) {
        //取消按钮
        vm.animateShow = false;
        $("#btn_s .btn").removeClass("disabled");
        layer.close(index);
      },
      cancel: function (index, layero) {
        //关闭按钮
        vm.animateShow = false;
        $("#btn_s .btn").removeClass("disabled");
        layer.close(index);
      }
    })
  })


  $("#review_btn").click(function () {
    if ($(this).hasClass("disabled")) {
      return;
    } else {
      if (!templateTreeNode) {
        layer.msg('请打开模版再操作', {icon: 0, time: 1000});
        return;
      }
      if (vm.preview_title == "退出预览") {
        vm.leavePreview();
      } else {
        vm.enterPreview();
      }
    }
  })

  $("#submit_btn").click(function () {
    if ($(this).hasClass("disabled")) {
      return;
    } else {
      vm.saveTemplateSetting();
    }
  })
}


/***************模板编辑区，数据处理*******************/
var vm = new Vue({
  el: "#center_area",
  data: {
    /*动画编辑框是否打开*/
    animateShow: false,
    btnIsShow: true,
    /*动画1*/
    animate1: {},
    /*动画2*/
    animate2: {},
    /*动画3*/
    animate3: {},
    animate4: {},
    isShowEditTemplate: false,
    style1: {},
    style2: {},
    style3: {},
    style4: {},
    style5: {},
    style6: {},
    style7: {},
    style8: {},
    edit: {
      width: 0,
      height: 0,
      opacity: 10,
    },
    preview_title: "预览效果"
  },
  methods: {
    /**打开新模版后，初始化动画设置界面*/
    initAnimatePanel: function () {
      $(".material_animate").empty().filter(".m_a_active").removeClass("m_a_active");

      var animateName;
      var templateId = templateTreeNode.id;
      for (var i = 1; i < 5; i++) {
        animateName = "animate" + i;
        this[animateName] = {
          seq: null,
          materialSeq: null,
          windPower: 0,
          speed: 2,
          count: 1,
          size: 30,
          opacity: 10,
          templateSeq: templateId
        }
      }

    },
    /*打开新模板，无数据时重置模版*/
    initEditTemplate: function () {
      //清空并停止动画
      window.snowStopFlag = true;
      $("#previewAnimation").hide().empty();

      //清空模版图片,清除选中状态
      $(".static_material").empty().removeClass("s_m_active");

      //重置模版样式
      this.style1 = {
        width: "30%",
        height: "10%",
        opacity: 1,
        display: "block"
      };
      this.style2 = {
        width: "40%",
        height: "10%",
        opacity: 1,
        display: "block"
      };
      this.style3 = {
        width: "30%",
        height: "10%",
        opacity: 1,
        display: "block"
      };
      this.style4 = {
        width: "30%",
        height: "60%",
        opacity: 1,
        display: "block"
      };
      this.style5 = {
        width: "30%",
        height: "60%",
        opacity: 1,
        display: "block"
      };
      this.style6 = {
        width: "30%",
        height: "10%",
        opacity: 1,
        display: "block"
      };
      this.style7 = {
        width: "40%",
        height: "10%",
        opacity: 1,
        display: "block"
      };
      this.style8 = {
        width: "30%",
        height: "10%",
        opacity: 1,
        display: "block"
      }
    },
    animateContentFilter: function (animate) {
      for (var key in animate) {
        switch (key) {
          case "windPower":
            if (animate[key] < -5) animate[key] = -5;
            if (animate[key] > 5) animate[key] = 5;
            break;
          case "speed":
            if (animate[key] < 0) animate[key] = 0;
            if (animate[key] > 5) animate[key] = 5;
            break;
          case "count":
            if (animate[key] < 1) animate[key] = 1;
            if (animate[key] > 10) animate[key] = 10;
            break;
          case "size":
            if (animate[key] < 10) animate[key] = 10;
            if (animate[key] > 80) animate[key] = 80;
            break;
          case "opacity":
            if (animate[key] < 1) animate[key] = 1;
            if (animate[key] > 10) animate[key] = 10;
            break;

        }
      }
    },
    /*提交动画编辑内容*/
    submitAnimateContent: function () {
      var arr = [];
      if (this.animate1.materialSeq || this.animate1.seq) {
        this.animateContentFilter(this.animate1);
        arr.push(this.animate1);
      }
      if (this.animate2.materialSeq || this.animate2.seq) {
        this.animateContentFilter(this.animate2);
        arr.push(this.animate2);
      }
      if (this.animate3.materialSeq || this.animate3.seq) {
        this.animateContentFilter(this.animate3);
        arr.push(this.animate3);
      }
      if (this.animate4.materialSeq || this.animate4.seq) {
        this.animateContentFilter(this.animate4);
        arr.push(this.animate4);
      }

      //数据为空，不提交
      if (arr.length == 0) {
        return;
      }

      $.ajax({
        type: "POST",
        url: getProjectPath() + "/sys/animateContent/add",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(arr),
        success: function (data) {
          if (data.code == 1) {
            layer.msg('动画上传成功', {icon: 6});
          } else {
            layer.msg('动画上传失败，请重试或联系管理员', {icon: 5});
          }
        }
      })


    },
    /*点击动画设置按钮调用方法*/
    getDataAfterOpenAnimate: function () {

      $.ajax({
        url: getProjectPath() + "/sys/animateContent/selectByTemplateSeq/" + templateTreeNode.id,
        type: "GET",
        cache: false,
        dataType: 'json',
        success: function (data) {
          if (data.code == 1) {
            var list = data.result;
            if (list && list.length > 0) {
              var item;
              var suffix;
              var animate;
              for (var i in list) {
                suffix = +i + 1;
                animate = "animate" + suffix;
                item = list[i];
                vm[animate].seq = item.seq;
                vm[animate].materialSeq = item.materialSeq;
                vm[animate].windPower = item.windPower;
                vm[animate].speed = item.speed;
                vm[animate].count = item.count;
                vm[animate].size = item.size;
                vm[animate].opacity = item.opacity;

                //创建小图片
                if (item.material) {
                  var image = new Image();
                  image.src = getHostPath() + materialImagePath + item.material.path;
                  var id = "#am" + suffix;
                  $(id).append(image);
                }
                if (i == 3) break;
              }

            }
          } else {
            layer.msg('动画数据获取失败，请重试或联系管理员', {icon: 5});
          }
        }
      });

    },
    /*静态素材设置保存按钮调用*/
    saveTemplateSetting: function () {
      if (!templateTreeNode) {
        layer.msg('请打开模版再操作', {icon: 0, time: 1000});
        return;
      }
      //总部模版，全国权限以下用户无权修改
      if (notEditPermission(templateTreeNode)) return;

      var arr = [];
      //获取模版静态设置
      var oImage;
      var selector;
      var style;
      for (var i = 1; i < 9; i++) {
        selector = "#m" + i;
        style = "style" + i;
        if ((oImage = $(selector).children("img")[0])) {
          var wv = this[style].width;
          var hv = this[style].height;
          arr.push({
            position: i,
            opacity: this[style].opacity * 10,
            wper: wv.substring(0, wv.length - 1),
            hper: hv.substring(0, hv.length - 1),
            templateSeq: templateTreeNode.id,
            materialSeq: oImage.id.substring(2)
          });
        }
      }

      if (arr.length == 0) {
        //根据模版seq后台删除所有设置
        $.get(getProjectPath() + "/sys/festivalStaticContent/delAll?templateSeq=" + templateTreeNode.id, function (data) {
          if (data.code == 1) {
            layer.msg('保存成功', {icon: 6});
          }
        })
        return;
      }

      $.ajax({
        type: "POST",
        url: getProjectPath() + "/sys/festivalStaticContent/add",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(arr),
        success: function (data) {
          if (data.code == 1) {
            layer.msg('静态素材设置上传成功', {icon: 6});
          } else {
            layer.msg('静态素材设置上传失败，请重试或联系管理员', {icon: 5});
          }
        }
      })
    },
    enterPreview: function () {
      //隐藏无图片的区块
      var oImage;
      var selector;
      var style;
      for (var i = 1; i < 9; i++) {
        selector = "#m" + i;
        var $imageWrap = $(selector);
        style = "style" + i;
        if (!(oImage = $imageWrap.children("img")[0])) {
          vm[style].display = "none";
        }
        $imageWrap.removeClass("material_edit_border");
      }
      this.addAnimation();
      this.preview_title = "退出预览";
      //禁用动画编辑按钮
      $("#animal_btn").addClass("disabled");
      //去除素材选中状态
      $("#edit_content .s_m_active").removeClass("s_m_active");
      //显示动画层
      $("#previewAnimation").show();
    },
    /*退出预览状态，显示所有区块*/
    leavePreview: function () {
      window.snowStopFlag = true;

      var style;
      for (var i = 1; i < 9; i++) {
        style = "style" + i;
        vm[style].display = "block";
      }
      //素材编辑框
      $("#edit_content").children(".static_material").addClass("material_edit_border");
      $("#previewAnimation").hide().empty();
      this.preview_title = "预览效果";
      $("#animal_btn").removeClass("disabled");
    },
    addAnimation: function () {
      $.post(getProjectPath() + "/sys/animateContent/selectByTemplateSeq/" + templateTreeNode.id, function (data) {
        if (data.code == 1) {
          if (data.result) {
            var animation;
            var $animationWrap = $("#previewAnimation");
            window.snowStopFlag = false;
            for (var i in data.result) {
              animation = data.result[i];
              var material;
              if ((material = animation.material)) {
                var imageSrc = getHostPath() + materialImagePath + material.path;
                var canvas = document.createElement("CANVAS");
                $animationWrap.append(canvas);
                $(canvas).let_it_snow({
                  windPower: animation.windPower,
                  speed: animation.speed,
                  count: animation.count,
                  size: animation.size,
                  opacity: animation.opacity / 10,
                  imageSrc: imageSrc
                });
              }
            }

          }
        }
      })
    }
  }
})


//监听编辑区双击事件
function listenEditAreaDBLClick() {
  $("#edit_content").on("dblclick", ".static_material", function (e) {
    e.preventDefault();

    //避免连续单击取消选中状态

    if (!$(this).hasClass("s_m_active")) {
      $(this).addClass("s_m_active");
    }

    //编辑区:区块id号
    var areaId = this.id.substring(1);
    var style = "style" + areaId;
    var wv = vm[style].width;
    var hv = vm[style].height;
    vm.edit.width = wv.substring(0, wv.length - 1);
    vm.edit.height = hv.substring(0, hv.length - 1);
    vm.edit.opacity = vm[style].opacity * 10;
    var $area = $(this);

    layer.open({
      title: areaId ? "编辑: 区块" + areaId : "动画设置",
      type: 1,
      area: ['500px', '300px'],
      content: $("#static_material_setting"),
      btn: ['确定', '取消', '删除素材'],
      closeBtn: 1,
      shade: 0,
      move: true,
      resize: false,
      yes: function (index, layero) {
        var wh = vm.edit.width;
        var ht = vm.edit.height;
        var op = vm.edit.opacity;

        if (isNaN(wh)) {
          layer.alert("宽度比例值无效，非数字", {icon: 0})
        } else {
          if (wh >= 1 && wh <= 100) {
            vm[style].width = vm.edit.width + "%";
          } else {
            layer.alert("宽度值 " + wh + " 超出范围[1,100]，设置无效", {icon: 0})
          }

        }
        if (isNaN(ht)) {
          layer.alert("高度比例值无效，非数字", {icon: 0})
        } else {
          if (ht >= 1 && ht <= 100) {
            vm[style].height = vm.edit.height + "%";
          } else {
            layer.alert("高度值 " + ht + " 超出范围[1,100]，设置无效", {icon: 0})
          }
        }
        if (isNaN(op)) {
          layer.alert("透明度值无效，非数字", {icon: 0})
        } else {
          if (op >= 1 && op <= 10) {
            vm[style].opacity = vm.edit.opacity / 10;
          } else {
            layer.alert("宽度值 " + op + " 超出范围[1,10]，设置无效", {icon: 0})
          }
        }

        layer.close(index);
      },
      btn2: function (index, layero) {
        //取消按钮
        layer.close(index);
      },
      btn3: function (index, layero) {
        //删除素材
        $area.empty();
        layer.close(index);
      },
      cancel: function (index, layero) {
        //关闭按钮
        layer.close(index);
      }
    })
  })
}

/*监听layer输入框回车事件，未完成*/
function listenLayerprompt() {
  $("input.layui-layer-input").on('keydown', function (e) {
    if (e.which == 13) {

      layer.close(1);
    }
  });
}


function initDateRangePicker() {
  //初始化时间控件
  $(function () {

    $("#dateRangePicker1").daterangepicker(
      {
        // singleDatePicker: true,
        linkedCalendars: false,
        showDropdowns: true,
        autoUpdateInput: false,
        showISOWeekNumbers: true,
        showWeekNumbers: true,
        timePicker24Hour: true,
        timePicker: true,
        autoApply: true,
        locale: {
          format: "YYYY/MM/DD HH:mm:ss",
          separator: ' ~ ',
          applyLabel: "应用",
          cancelLabel: "取消",
          resetLabel: "重置",
          customRangeLabel: '选择时间',
          daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
          monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
          firstDay: 0
        }

      }).on('apply.daterangepicker', function (ev, picker) {
      extraVM.startTime = picker.startDate.format(picker.locale.format);
      extraVM.endTime = picker.endDate.format(picker.locale.format);
      $('#datePickerModal').modal('hide');
    }).on('cancel.daterangepicker', function (ev, picker) {
      extraVM.startTime = "";
      extraVM.endTime = "";
    });
  })


}

var extraVM = new Vue({
  el: "#datePickerVM",
  data: {
    datePickerTitle: "设置模版生效时间: ",
    startTime: "",
    endTime: "",
    inputTime: "",
    btnIsShow: true,
  },
  computed: {
    dateRange: function () {
      return this.startTime && this.startTime.length > 0 ? this.startTime + " ~ " + this.endTime : "";
    }
  },
  mounted: function () {

  },
  methods: {
    setTemplateValidTime: function () {

      if (!templateTreeNode) {
        layer.msg("请先打开模版", {icon: 0});
        return;
      }
      var tid = templateTreeNode.tId;
      var treeId = tid.substring(0, tid.indexOf("_"));

      //总部模版，全国权限以下用户无权修改
      if (notEditPermission(templateTreeNode)) return;

      if (!this.startTime || this.startTime.length < 1) {
        layer.msg("请先选择时间范围", {icon: 0})
        return;
      }
      if (!this.endTime || this.endTime.length < 1) return;

      var seqs = getALlShopCheckedNodes();
      // console.log("seqs: ", seqs);
      if (!seqs || seqs.length < 1) {
        layer.alert("未选择门店", {icon: 0})
        return;
      }

      $.post(getProjectPath() + "/sys/shopAnimationControl/addNew", {
        sValidDate: new Date(extraVM.startTime),
        eValidDate: new Date(extraVM.endTime),
        animationSeq: templateTreeNode.id,
        shopSeqs: seqs
      }, function (data, status) {
        if (data.code == 1) {
          extraVM.inputTime = new Date().format("yyyy-MM-dd HH:mm:ss");
          layer.msg('模版应用设置成功', {icon: 6});
          if (treeId == "tree1") {
            loadTree1();
          } else {
            loadTree2();
          }
        } else {
          layer.msg('模版应用设置失败，请重试或联系管理员', {icon: 0});
        }
      })

    }
  }
})

function listenModalShowEvent() {
  $('#datePickerModal').on('show.bs.modal', function () {
    if (templateTreeNode) {
      extraVM.datePickerTitle = "设置模版生效时间: " + templateTreeNode.name;
    } else {
      layer.msg("请先打开模版", {icon: 0})
    }
  })
}

/*打开设置有效时间的弹窗*/
function openDateRangeSettingPanel() {
  if (templateTreeNode) {
    $("#datePickerModal").modal("show");
  } else {
    layer.msg("请先打开模版", {icon: 0})
  }
}

/**********使模版生效***************/
function validTemplate() {
  extraVM.setTemplateValidTime();
}

function loadUserRole() {
  $.post(getProjectPath() + "/sys/shopTree/getUserRoleSeq", function (data) {
    if (data.code == 1) {
      userRoleSeq = data.result;
    }
  })
}

/*监听动画素材双击事件*/
function listenDBLAnimationMaterial() {
  $(".material_animate").dblclick(function () {
    var id = this.id.substring(2);
    var $animationWrap = $(this);

    layer.confirm('是否删除', {icon: 3, title: '询问'},
      function (index) {
        $animationWrap.empty();
        var animateName = "animate" + id;
        vm[animateName].materialSeq = null;
        vm[animateName].windPower = 0;
        vm[animateName].size = 30;
        vm[animateName].speed = 2;
        vm[animateName].count = 1;
        vm[animateName].opacity = 10;
        layer.close(index);
      });
  })
}

/**
 * 分页组件
 * @param selector
 * @param pages 总页数
 * @param pageSize 每页数量
 * @param callback 按页查询操作
 */
function pagination(selector, pages, pageSize, callback) {
  $(selector).jqPaginator({
    totalPages: pages,
    visiblePages: 7,
    currentPage: 1,
    first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
    prev: '<li class="prev"><a href="javascript:void(0);">&laquo;<\/a><\/li>',
    next: '<li class="next"><a href="javascript:void(0);">&raquo;<\/a><\/li>',
    last: '<li class="last"><a href="javascript:void(0);">尾页<\/a><\/li>',
    page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
    onPageChange: function (num, type) {
      // console.log('当前第' + num + '页', type)
      //分页查询
      callback && callback(num, pageSize);
    }
  })

}

function listenShopTreeAction() {
  //勾选全部门店
  $("#shop_check_all_btn").click(function () {
    zTree3.checkAllNodes(true);
  })
  //勾选所有勾选
  $("#shop_no_check_btn").click(function () {
    zTree3.checkAllNodes(false);
  })
}

/*获取模版关联的背景*/
function getTemplateBackground(templateSeq) {
  if (templateSeq == null) return;

  $.get(getProjectPath() + "/sys/ScreenBackground/get/" + templateSeq, function (data) {
    if (data.result && data.result.material) {
      var material = data.result.material;
      //设置背景图
      var imgSrc = getHostPath() + materialImagePath + material.path;
      $("#screen").css({"background-image": "url('" + imgSrc + "')"});
    }
  })
}

/*设置且保存新的模版背景*/
function addNewTemplateBackground(imageEl) {
  //设置背景图
  var imgSrc = imageEl.src;
  $("#screen").css({"background-image": "url('" + imgSrc + "')"});

  //获取图片的序号
  var imageSeq = parseInt(imageEl.id.substring(2));

  var backgroundObj = {
    templateSeq: templateTreeNode.id,
    materialSeq: imageSeq
  }

  // console.log("设置屏幕背景：",imageEl,backgroundObj)

  //上传背景信息
  $.post(getProjectPath() + "/sys/ScreenBackground/add", backgroundObj,
    function (data) {
      if (data.code == 1) {
        // layer.msg("背景图片上传成功", {icon: 6});
      } else {
        layer.msg("背景图片上传失败,请重试或联系管理员", {icon: 5});
      }
    })
}

/*监听背景操作按钮*/
function listenBGBtn() {

  $("#bg_del_btn").click(function () {

    layer.confirm('您确定要删除背景图吗?', {icon: 3, title: '请确认'},
      function (index) {
        $.get(getProjectPath() + "/sys/ScreenBackground/del/" + templateTreeNode.id, function (data) {
          if (data.code == 1) {
            //执行删除背景操作
            $("#screen").get(0).removeAttribute("style");
            layer.msg("背景删除成功！", {icon: 6, time: 1000})
          }
        })
        layer.close(index);
      }, function (index) {
        // console.log("取消")
      });


  })

  $("#bg_add_btn").click(function () {

    if ($("#bg_add_btn").hasClass("bg-editing")) {
      //退出背景操作
      $("#bg_add_btn").addClass("btn-default").removeClass("btn-success bg-editing").text("背景设置");
    } else {
      //添加背景操作标记
      $("#bg_add_btn").addClass("btn-success bg-editing").removeClass("btn-default").text("退出背景");
    }

  })
}

/************入口****************/
$(function () {
  loadUserRole();
  loadTree1();
  loadTree2();
  loadShopSelectTree();
  initUserMaterialPagination();
  initCompanyMaterialPagination();
  initDateRangePicker();
  listenModalShowEvent();
  listenEditAreaDBLClick();
  listenMaterialImageClick();
  listenImageUpload();
  listenBtns();
  listenDBLAnimationMaterial();
  listenShopTreeAction();
  listenBGBtn();
})

