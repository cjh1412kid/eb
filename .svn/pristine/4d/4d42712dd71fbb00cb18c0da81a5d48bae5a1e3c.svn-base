
var vm = new Vue({
    el: '#rrapp',
    data: {
    	year:'',
    	years:[],
    	deadlineRaiseRateEntity:'',
    	deadlineRaiseRateDate:'',
    	deadlineRaiseRateSaleOutRate:'',
    	deadlineRaiseRateSalesDays:'',
    	purchasePlans:'',
    	purchasePlansChoose:'',
    	firstOrdersChoose:'',
    	patchedsChoose:'',
    	reservesChoose:'',
    	allPeriods:[],
    	allSXValues:[],
    	sizeAllotTemplates:[],
    	allSizes:[],
    	SXValue:'',
    	period:'',
    	periodSeq:'',
    	minSalesDays:'',
    	patchAdvanceDays:'',
    	springDeep:'',
    	springDeepSaleOutRate:'',
    	springShallow:'',
    	springShallowSaleOutRate:'',
    	halfCold:'',
    	halfColdSaleOutRate:'',
    	fullCold:'',
    	fullColdSaleOutRate :'',
    	autumnShallow:'',
    	autumnShallowSaleOutRate:'',
    	autumnDeep:'',
    	autumnDeepSaleOutRate:'',
    	singleBoot:'',
    	singleBootSaleOutRate:'',
    	twoCotton:'',
    	twoCottonSaleOutRate:'',
    	bigCotton:'',
    	bigCottonSaleOutRate:'',
    },
    methods: {
    	loadYear:function(){
    		var myDate = new Date();
    	    var tYear = myDate.getFullYear();
    	    var years=[];
    	    for (var i = 5; i >0; i--) {
    	    	years.push(tYear-i)
			}
    	    years.push(tYear)
    	    years.push(tYear+1)
    	    this.years=years;
    	    this.year=tYear
    	    this.loadRecord();
    	},
    	dateFormat:function(time){
    		var date=new Date(time);
    		var year=date.getFullYear();
    		var month= date.getMonth()+1
    		var day=date.getDate()
    		return year+"/"+month+"/"+day
    	},
    	dateFormat2:function(time){
    		if(time==""){
    			return;
    		}
    		var date=new Date(time);
    		var year=date.getFullYear();
    		var month= date.getMonth()+1
    		var day=date.getDate()
    		return year+"-"+month+"-"+day
    	},
    	goto:function(){
    		var rootUrl=$("#rootUrl").val();
    		parent.location.href=rootUrl+"/index.html#modules/order_platform/deadlineraiserate.html";
    	},
    	yearSelect:function(item){
    		this.year=item
    		this.deadlineRaiseRateEntity='';
    		this.deadlineRaiseRateDate='';
    		this.deadlineRaiseRateSaleOutRate='';
    		this.deadlineRaiseRateSalesDays='';
    		this.purchasePlans='';
    		this.purchasePlansChoose='';
    		this.firstOrdersChoose='';
    		this.patchedsChoose='';
    		this.reservesChoose='';
    		this.allPeriods=[];
    		this.allSXValues=[];
    		this.sizeAllotTemplates=[];
    		this.allSizes=[];
    		this.SXValue='';
    		this.period='';
    		this.periodSeq='';
    		this.minSalesDays='';
    		this.patchAdvanceDays='';
    		this.springDeep='';
    		this.springDeepSaleOutRate='';
    		this.springShallow='';
    		this.springShallowSaleOutRate='';
    		this.halfCold='';
    		this.halfColdSaleOutRate='';
    		this.fullCold='';
    		this.fullColdSaleOutRate =''
    		this.autumnShallow=''
    		this.autumnShallowSaleOutRate=''
    		this.autumnDeep=''
    		this.autumnDeepSaleOutRate=''
    		this.singleBoot=''
    		this.singleBootSaleOutRate=''
    		this.twoCotton=''
    		this.twoCottonSaleOutRate=''
    		this.bigCotton=''
    		this.bigCottonSaleOutRate=''
    		 this.loadRecord();
    	},
    	loadRecord:function(){
    		$.ajax({
				type : "get",
				url : baseURL + "sys/importData/getRecords",
				data:{
					year:this.year
				     },
				success : function(r){
					console.log(r)
					var deadlineRaiseRateEntity=r.deadlineRaiseRateEntity
					var purchasePlans=r.purchasePlans
					var FirstOrders=r.FirstOrders;
					var Patcheds=r.Patcheds;
					var Reserves=r.Reserves;
					var sizeAllotTemplates=r.sizeAllotTemplates;
					var allPeriods=r.allPeriods;
					var allSXValues=r.SXValues;
					var allSizes=r.allSizes
					vm.deadlineRaiseRateEntity=deadlineRaiseRateEntity
					vm.purchasePlans=purchasePlans
					vm.sizeAllotTemplates=sizeAllotTemplates
					vm.allPeriods=allPeriods;
					vm.allSXValues=allSXValues
					vm.allSizes=allSizes
					if(allPeriods&&allPeriods.length>0){
						vm.period=allPeriods[0].name
						vm.periodSeq=allPeriods[0].periodSeq
					}
					if(allSXValues){
						vm.SXValue=allSXValues[0]
					}
					if(deadlineRaiseRateEntity){
						vm.minSalesDays=deadlineRaiseRateEntity.minSalesDays
						vm.patchAdvanceDays=deadlineRaiseRateEntity.patchAdvanceDays
						vm.springDeep=deadlineRaiseRateEntity.springDeep
						vm.springDeepSaleOutRate=deadlineRaiseRateEntity.springDeepSaleOutRate
						vm.springShallow=deadlineRaiseRateEntity.springShallow
						vm.springShallowSaleOutRate=deadlineRaiseRateEntity.springShallowSaleOutRate
						vm.halfCold=deadlineRaiseRateEntity.halfCold
						vm.halfColdSaleOutRate=deadlineRaiseRateEntity.halfColdSaleOutRate
						vm.fullCold=deadlineRaiseRateEntity.fullCold
						vm.fullColdSaleOutRate =deadlineRaiseRateEntity.fullColdSaleOutRate 
						vm.autumnShallow=deadlineRaiseRateEntity.autumnShallow
						vm.autumnShallowSaleOutRate=deadlineRaiseRateEntity.autumnShallowSaleOutRate
						vm.autumnDeep=deadlineRaiseRateEntity.autumnDeep
						vm.autumnDeepSaleOutRate=deadlineRaiseRateEntity.autumnDeepSaleOutRate
				    	vm.singleBoot=deadlineRaiseRateEntity.singleBoot
				    	vm.singleBootSaleOutRate=deadlineRaiseRateEntity.singleBootSaleOutRate
				    	vm.twoCotton=deadlineRaiseRateEntity.twoCotton
				    	vm.twoCottonSaleOutRate=deadlineRaiseRateEntity.twoCottonSaleOutRate
				    	vm.bigCotton=deadlineRaiseRateEntity.bigCotton
				    	vm.bigCottonSaleOutRate=deadlineRaiseRateEntity.bigCottonSaleOutRate
					if(deadlineRaiseRateEntity.springDeep||deadlineRaiseRateEntity.springShallow||deadlineRaiseRateEntity.halfCold||deadlineRaiseRateEntity.fullCold||deadlineRaiseRateEntity.autumnShallow||deadlineRaiseRateEntity.autumnDeep||deadlineRaiseRateEntity.singleBoot||deadlineRaiseRateEntity.twoCotton||deadlineRaiseRateEntity.bigCotton){
						vm.deadlineRaiseRateDate=true
					}else{
						vm.deadlineRaiseRateDate=false	
					}
					if(deadlineRaiseRateEntity.springDeepSaleOutRate||deadlineRaiseRateEntity.springShallowSaleOutRate||deadlineRaiseRateEntity.halfColdSaleOutRate||deadlineRaiseRateEntity.fullColdSaleOutRate||deadlineRaiseRateEntity.autumnShallowSaleOutRate||deadlineRaiseRateEntity.autumnDeepSaleOutRate||deadlineRaiseRateEntity.singleBootSaleOutRate||deadlineRaiseRateEntity.twoCottonSaleOutRate||deadlineRaiseRateEntity.bigCottonSaleOutRate){
						vm.deadlineRaiseRateSaleOutRate=true
					}else{
						vm.deadlineRaiseRateSaleOutRate=false
					}
					if(deadlineRaiseRateEntity.minSalesDays){
						vm.deadlineRaiseRateSalesDays=true
					}else{
						vm.deadlineRaiseRateSalesDays=false
					}
					}else{
						vm.deadlineRaiseRateDate=false	
						vm.deadlineRaiseRateSaleOutRate=false
						vm.deadlineRaiseRateSalesDays=false
					}
					if(purchasePlans.length>0){
						vm.purchasePlansChoose=true
					}else{
						vm.purchasePlansChoose=false
					}
					if(FirstOrders.length>0){
						vm.firstOrdersChoose=true
					}else{
						vm.firstOrdersChoose=false
					}
					if(Patcheds.length>0){
						vm.patchedsChoose=true
					}else{
						vm.patchedsChoose=false
					}
					if(Reserves.length>0){
						vm.reservesChoose=true
					}else{
						vm.reservesChoose=false
					}
				}})
    		
    	},
    	periodSelect:function(period){
    		vm.period=period.name
    		vm.periodSeq=period.periodSeq
    		$.ajax({
				type : "get",
				url : baseURL + "sys/importData/getSXValues",
				data:{
					periodSeq:period.periodSeq
				     },
				success : function(r){
					var FirstOrders=r.FirstOrders;
					var Patcheds=r.Patcheds;
					var Reserves=r.Reserves;
					var allSXValues=r.SXValues;
					vm.allSXValues=allSXValues
					if(allSXValues){
						vm.SXValue=allSXValues[0]
					}
					if(FirstOrders){
						vm.firstOrdersChoose=true
					}else{
						vm.firstOrdersChoose=false
					}
					if(Patcheds){
						vm.patchedsChoose=true
					}else{
						vm.patchedsChoose=false
					}
					if(Reserves){
						vm.reservesChoose=true
					}else{
						vm.reservesChoose=false
					}
				}
				})
    		
    	},
    	SXValueSelect:function(SXValue){
    		vm.SXValue=SXValue
    		$.ajax({
				type : "get",
				url : baseURL + "sys/importData/getOrders",
				data:{
					periodSeq:period.periodSeq,
					SXValue:SXValue
				     },
				success : function(r){
					var FirstOrders=r.FirstOrders;
					var Patcheds=r.Patcheds;
					var Reserves=r.Reserves;
					if(FirstOrders){
						vm.firstOrdersChoose=true
					}else{
						vm.firstOrdersChoose=false
					}
					if(Patcheds){
						vm.patchedsChoose=true
					}else{
						vm.patchedsChoose=false
					}
					if(Reserves){
						vm.reservesChoose=true
					}else{
						vm.reservesChoose=false
					}
				}
				})
    		
    	},
    	uploadSizeAllotTemplate : function () {
//            parent.window.showLoading();
            $('#goodsExcelForm').ajaxSubmit({
                url: baseURL + 'sys/importData/uploadSizeAllotTemplate',
                dataType: 'json',
                success: function (r) {
                	console.log(r)
                    if (r.code === 1) {
                        alert(r.msg, function () {
                            location.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
//                    $("#goodsExcelForm").resetForm();
//                    parent.window.hideLoading();
                },
                error: function (r) {
                    alert("导入配码出错！");
//                    $("#goodsExcelForm").resetForm();
//                    parent.window.hideLoading();
                }
            });
        },
        uploadGoodsData: function () {
//          parent.window.showLoading();
          $('#goodsDataExcelForm').ajaxSubmit({
              url: baseURL + 'sys/importData/uploadGoodsData',
              dataType: 'json',
              success: function (r) {
                  if (r.code === 1) {
                      alert(r.msg, function () {
                          location.reload();
                      });
                  } else {
                      alert(r.msg);
                  }
//                  $("#goodsExcelForm").resetForm();
//                  parent.window.hideLoading();
              },
              error: function (r) {
                  alert("导入配码出错！");
//                  $("#goodsExcelForm").resetForm();
//                  parent.window.hideLoading();
              }
          });
      },
      uploadReplenishment: function () {
//        parent.window.showLoading();
        $('#replenishmentExcelForm').ajaxSubmit({
            url: baseURL + 'sys/importData/replenishment',
            dataType: 'json',
            success: function (r) {
                if (r.code === 1) {
                    alert(r.msg, function () {
                        location.reload();
                    });
                } else {
                    alert(r.msg);
                }
//                $("#goodsExcelForm").resetForm();
//                parent.window.hideLoading();
            },
            error: function (r) {
                alert("导入配码出错！");
//                $("#goodsExcelForm").resetForm();
//                parent.window.hideLoading();
            }
        });
    },
    uploadpurchasePlan: function () {
//      parent.window.showLoading();
      $('#purchasePlanFileExcelForm').ajaxSubmit({
          url: baseURL + 'sys/importData/purchasePlan',
          dataType: 'json',
          success: function (r) {
              if (r.code ===1) {
                  alert(r.msg, function () {
                      location.reload();
                  });
              } else {
                  alert(r.msg);
              }
//              $("#goodsExcelForm").resetForm();
//              parent.window.hideLoading();
          },
          error: function (r) {
              alert("导入配码出错！");
//              $("#goodsExcelForm").resetForm();
//              parent.window.hideLoading();
          }
      });
  },
  uploadEveryPurchasePlan: function () {
//    parent.window.showLoading();
    $('#everyPurchasePlanFileExcelForm').ajaxSubmit({
        url: baseURL + 'sys/importData/everyPurchasePlan',
        dataType: 'json',
        success: function (r) {
            if (r.code ===1) {
                alert(r.msg, function () {
                    location.reload();
                });
            } else {
                alert(r.msg);
            }
//            $("#goodsExcelForm").resetForm();
//            parent.window.hideLoading();
        },
        error: function (r) {
            alert("导入配码出错！");
//            $("#goodsExcelForm").resetForm();
//            parent.window.hideLoading();
        }
    });
},
     
    },
    created: function () {
    	this.loadYear();
    },
    
});
