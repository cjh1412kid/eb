$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/patch/list',
        datatype: "json",
        colModel: [			
			{ label: '序号', name: 'seq', index: 'Seq', width: 50, key: true },
			{ label: '年份', name: 'year', index: 'Year', width: 80 },
			{ label: '周', name: 'week', index: 'Week', width: 80 },
			{ label: '开始日期', name: 'startDate', index: 'StartDate', width: 80 },
			{ label: '结束日期', name: 'endDate', index: 'EndDate', width: 80 },
			{ label: '大区', name: 'areaName', index: 'AreaSeq', width: 80 },
			{ label: '分公司', name: 'branchOfficeName', index: 'BranchOfficeSeq', width: 80 },
			{ label: '季节', name: 'periodName', index: 'PeriodSeq', width: 80 },
			{ label: '品类', name: 'sxValue', index: 'SxValue', width: 80 },
			{ label: '货号', name: 'shoeID', index: 'ShoeID', width: 80 },
			{ label: '补单数量', name: 'patchNum', index: 'PatchNum', width: 80 },
			{ label: '状态', name: 'stateName', index: 'State', width: 80 },
			{ label: '入库时间', name: 'inputTime', index: 'InputTime', width: 80 }
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
		patch: {}
	},
	methods: {
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});