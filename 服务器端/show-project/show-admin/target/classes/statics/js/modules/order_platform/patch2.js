//$(function () {
//    $("#jqGrid").jqGrid({
//        url: baseURL + 'sys/patch/list',
//        datatype: "json",
//        colModel: [			
//			{ label: '序号', name: 'seq', index: 'Seq', width: 50, key: true },
//			{ label: '年份', name: 'year', index: 'Year', width: 80 },
//			{ label: '周', name: 'week', index: 'Week', width: 80 },
//			{ label: '开始日期', name: 'startDate', index: 'StartDate', width: 80 },
//			{ label: '结束日期', name: 'endDate', index: 'EndDate', width: 80 },
//			{ label: '大区', name: 'areaName', index: 'AreaSeq', width: 80 },
//			{ label: '分公司', name: 'branchOfficeName', index: 'BranchOfficeSeq', width: 80 },
//			{ label: '季节', name: 'periodName', index: 'PeriodSeq', width: 80 },
//			{ label: '品类', name: 'sxValue', index: 'SxValue', width: 80 },
//			{ label: '货号', name: 'shoeID', index: 'ShoeID', width: 80 },
//			{ label: '补单数量', name: 'patchNum', index: 'PatchNum', width: 80 },
//			{ label: '状态', name: 'stateName', index: 'State', width: 80 },
//			{ label: '入库时间', name: 'inputTime', index: 'InputTime', width: 80 }
//        ],
//		viewrecords: true,
//        height: 385,
//        rowNum: 10,
//		rowList : [10,30,50],
//        rownumbers: true, 
//        rownumWidth: 25, 
//        autowidth:true,
//        multiselect: true,
//        pager: "#jqGridPager",
//        jsonReader : {
//            root: "page.list",
//            page: "page.currPage",
//            total: "page.totalPage",
//            records: "page.totalCount"
//        },
//        prmNames : {
//            page:"page", 
//            rows:"limit", 
//            order: "order"
//        },
//        gridComplete:function(){
//        	//隐藏grid底部滚动条
//        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
//        }
//    });
//});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		patch: {},
		selectWeekName:'',
		weeks:[],
		selectPeriodName:'',
		periods:[],
		selectCategoryName:'',
		categorys:[],
		patchs:[],
		areas:[],
		admin:1,
	},
	methods: {
		getPeriodList : function() {
			$.get(baseURL + "sys/patch/period?week="+vm.selectWeekName, function(r) {
				console.log(r)
	            if (r.code === 1) {
	            	vm.periods=r.result
	            } else {
	            	alert(r.msg);
	            }
			});
		},
		getDateList:function(){
			$.get(baseURL + "sys/patch/weeks", function(r) {
	            if (r.code === 1) {
	            	vm.weeks=r.result
	            	if(r.result.length>0){
	            		vm.selectWeekName=r.result[0]
	            		vm.getPeriodList();
	            	}
	            } else {
	            	alert(r.msg);
	            }
			});
		},
		categoryList:function(){
			var selectPeriodName=vm.selectPeriodName;
			$.get(baseURL + "sys/baseInfo/categorys?periodName="+selectPeriodName, function(r) {
	            if (r.code === 1) {
	            	vm.categorys=r.result
	            } else {
	            	alert(r.msg);
	            }
			});
		},
		periodSelect:function(period){
			vm.selectPeriodName=period.name
			vm.periodSeq=period.seq
			this.categoryList();
			vm.selectCategoryName='';
			this.getData();
		},
		categorySelect:function(categoryName){
			vm.selectCategoryName=categoryName
			this.getData();
		},
		dateSelect:function(week){
			vm.selectWeekName=week
			this.getPeriodList();
			this.getData();
		},
		exportRecord:function(){
			var sxValue= vm.selectCategoryName;
			var week=vm.selectWeekName
			var periodSeq=vm.periodSeq
			if(sxValue&&week&&periodSeq){
				 var form = document.getElementById('exportRecord');
		            form.action = baseURL + "sys/patch/exportRecord";
		            form.sxValue.value =sxValue;
		            form.week.value =week;
		            form.periodSeq.value =periodSeq;
		            form.submit();
			}else{
				alert("条件未选择")
			}
		},
		getData:function(){
			var sxValue= vm.selectCategoryName;
			var week=vm.selectWeekName
			var periodSeq=vm.periodSeq
			if(sxValue&&week&&periodSeq){
			$.ajax({
				type : "get",
				url : baseURL + "sys/patch/patchList",
				data:{
					sxValue: vm.selectCategoryName,
					week:week,
					periodSeq:vm.periodSeq,
				     },
				success : function(r){
						if(r.code==1){
							console.log(r)
							vm.areas=r.area;
							vm.patchs=r.goods;
							vm.admin=r.admin
						}
				}
			})
			}
			
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.patch = {};
		},
		update: function (event) {
			var seq = getSelectedRow();
			if(seq == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(seq)
		},
		saveOrUpdate: function (event) {
			var url = vm.patch.seq == null ? "sys/patch/save" : "sys/patch/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.patch),
			    success: function(r){
			    	if(r.code === 1){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var seqs = getSelectedRows();
			if(seqs == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/patch/delete",
                    contentType: "application/json",
				    data: JSON.stringify(seqs),
				    success: function(r){
						if(r.code == 1){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(seq){
			$.get(baseURL + "sys/patch/info/"+seq, function(r){
                vm.patch = r.patch;
            });
		},
		
		submit:function(){
			var patchs=vm.patchs;
			var patchAll=[]
			for (var i = 0; i < patchs.length; i++) {
				var patchNums=patchs[i].patchNum;
				for (var j = 0; j < patchNums.length; j++) {
					var patchNum = patchNums[j];
					patchAll.push(patchNum)
				}
			}
			$.ajax({
				type: "POST",
			    url: baseURL + "sys/patch/updateAll",
                contentType: "application/json",
			    data: JSON.stringify(patchAll),
			    success: function(r){
					if(r.code == 1){
						alert('操作成功', function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
			
			
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
	},
	created:function() {
	//	this.getPeriodList();
		this.getDateList();
//		this.categoryList();
	},
});
function getFirstDayOfWeek (date) {
	   var weekday = date.getDay()||7; // 获取星期几,getDay()返回值是 0（周日） 到 6（周六）
										// 之间的一个整数。0||7为7，即weekday的值为1-7
		date.setDate(date.getDate()-weekday+1);// 往前算（weekday-1）天，年份、月份会自动变化
	   return date;
	 }
function getLastDayOfWeek (date) {
	   var weekday = date.getDay()||7; // 获取星期几,getDay()返回值是 0（周日） 到 6（周六）
										// 之间的一个整数。0||7为7，即weekday的值为1-7
		date.setDate(date.getDate()-weekday+8);// 往前算（weekday-1）天，年份、月份会自动变化
	   return date;
	 }
function getStartDayOfWeek (date) {
		date.setDate(date.getDate()-7);// 往前算（weekday-1）天，年份、月份会自动变化
	   return date;
}
function getEndDayOfWeek (date) {
	date.setDate(date.getDate()-8);// 往前算（weekday-1）天，年份、月份会自动变化
	   return date;
}
function getAddDayOfWeek (date) {
	date.setDate(date.getDate()+1);// 往前算（weekday-1）天，年份、月份会自动变化
	   return date;
}

	function timeFormat(date) {
		    if (!date || typeof(date) === "string") {
		          this.error("参数异常，请检查...");
		     }
		     var y = date.getFullYear(); // 年
		     var m = date.getMonth() + 1; // 月
		    var d = date.getDate(); // 日
		
		     return y + "/" + m + "/" + d;
		 }
