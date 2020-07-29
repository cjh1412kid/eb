$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/purchasePlan/list',
        datatype: "json",
        colModel: [			
			{ label: 'seq', name: 'seq', index: 'Seq', width: 50, key: true },
			{ label: '年份', name: 'year', index: 'Year', width: 80 }, 			
			{ label: '波次序号', name: 'periodSeq', index: 'PeriodSeq', width: 80 }, 			
			{ label: '品类', name: 'sxValue', index: 'SXValue', width: 80 }, 			
			{ label: '计划日期', name: 'planDate', index: 'PlanDate', width: 80 }, 			
			{ label: '计划数量', name: 'planNum', index: 'PlanNum', width: 80 }, 			
			{ label: '创建时间', name: 'inputTime', index: 'InputTime', width: 80 }		
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		purchasePlan: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.purchasePlan = {};
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
			var url = vm.purchasePlan.seq == null ? "sys/purchasePlan/save" : "sys/purchasePlan/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.purchasePlan),
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
				    url: baseURL + "sys/purchasePlan/delete",
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
			$.get(baseURL + "sys/purchasePlan/info/"+seq, function(r){
                vm.purchasePlan = r.purchasePlan;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});