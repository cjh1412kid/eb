$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/sizeAllotTemplate/list',
        datatype: "json",
        colModel: [			
			{ label: 'seq', name: 'seq', index: 'Seq', width: 50, key: true },
			{ label: '模板名', name: 'name', index: 'Name', width: 80 }, 			
			{ label: '南北编码标识（1南编码，2北编码）', name: 'nbFlag', index: 'NBFlag', width: 80 }, 			
			{ label: '总数量', name: 'totalNum', index: 'TotalNum', width: 80 }, 			
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
		sizeAllotTemplate: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sizeAllotTemplate = {};
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
			var url = vm.sizeAllotTemplate.seq == null ? "sys/sizeAllotTemplate/save" : "sys/sizeAllotTemplate/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sizeAllotTemplate),
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
				    url: baseURL + "sys/sizeAllotTemplate/delete",
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
			$.get(baseURL + "sys/sizeAllotTemplate/info/"+seq, function(r){
                vm.sizeAllotTemplate = r.sizeAllotTemplate;
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