
// IE
var SE_IE32_CLASSID="A791D428-6F85-47CD-B4D4-DF14E3FAE5DA";
var SE_IE32_CAB="SecEditCtl.YSB.x86.cab#version=3010001";
var SE_IE32_EXE="SecEditCtlYSBAllSetup.exe";
var SE_IE32_VERSION="3010001";

var SE_IE64_CLASSID="FB0DBE0E-1384-4E7C-A456-863EB0E9092A";
var SE_IE64_CAB="SecEditCtl.YSB.x64.cab#version=3010001";
var SE_IE64_EXE="SecEditCtlYSBAllSetup.exe";
var SE_IE64_VERSION="3010001";

// FF
var SE_FF32="SecEditCtlYSBAllSetup.exe";
var SE_FF32_VERSION="3010001";

var SE_FF64="SecEditCtlYSBAllSetup.exe";
var SE_FF64_VERSION="3010001";

var SE_VERSION = "3010001";

(function($)
{
	//多密码控件时共用一个服务器端随机数
	var shareServerRandom='';
	
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var reqUrl = localhostPaht+projectName;
	$.us = {  osBrowser:1,
				isInstalled:false,
				envPath: reqUrl+"/cert/cfca/",
				envClass: "ocx_style",
				envInstallClass: "ocx_style",
				envVersion:"3010001",
				envDownText:"<p style='border: 1px solid #666666; width: 160px; height: 14px;padding: 4px 4px;background-color:  #D3D3D3;'>请点此安装控件</p>",
				envSetupExe:"",
				envCab:"",
				envCLSID:""
			};
	
	$.extend($.us,
		{
			init: function() {
			    this.osBrowser = this.getOsBrowser();
				this.envVersion = this.getVersion();			    			
				this.isInstalled = this.getInstall();
			},
			getOsBrowser: function() {
				var userosbrowser;
				if( ( navigator.userAgent.match(/x86_64|Win64|WOW64/) || (navigator.cpuClass === 'x64'))  ){
					if(navigator.userAgent.match(/Safari|Firefox|Chrome/)){	
						userosbrowser=2; //windowsff
						this.envSetupExe=SE_FF64;
						SE_VERSION = SE_FF64_VERSION;				
					}else {
//						 if (!!window.ActiveXObject || "ActiveXObject" in window){
//							 var v = window.navigator.cpuClass;
//							 alert(v);
//							 alert(window.navigator.platform);
//						 }
						if(navigator.platform=="Win64"){
							userosbrowser=3;//windows64ie64
							this.envCLSID=SE_IE64_CLASSID;
							this.envCab=SE_IE64_CAB;
							this.envSetupExe=SE_IE64_EXE;
							SE_VERSION = SE_IE64_VERSION;
						}else{
							userosbrowser=1;//windows32ie32
							this.envCLSID=SE_IE32_CLASSID;
							this.envCab=SE_IE32_CAB;
							this.envSetupExe=SE_IE32_EXE;
							SE_VERSION = SE_IE32_VERSION;
						}
					}
				}else{
					if(navigator.userAgent.match(/Safari|Firefox|Chrome/)){
						userosbrowser=2; //windowsff
						this.envSetupExe=SE_FF32;
						SE_VERSION = SE_FF32_VERSION;
					}else{
						userosbrowser=1;//windows32ie32
						this.envCLSID=SE_IE32_CLASSID;
						this.envCab=SE_IE32_CAB;
						this.envSetupExe=SE_IE32_EXE;
						SE_VERSION = SE_IE32_VERSION;
					}
				}
				
				return userosbrowser;
			},
			getVersion: function() {
				return "3010001";
			},
			getInstall: function() {
				try {
					var pge_version = document.getElementById("SecEditBox1").GetVersion();
				}catch(e){
					return false;
				}
				return true;
			},
			setDownText:function(){
				this.envDownText="<p style='border: 1px solid #666666; width: 160px; height: 14px;padding: 4px 4px;background-color:  #D3D3D3;'>请点此升级控件</p>";
			},
			getDownHtml: function() {
				if (this.osBrowser==1 || this.osBrowser==3) {
					return '<span class="'+this.envInstallClass+'" style="text-align:center;"><a href="'+this.envPath+this.envSetupExe+'">'+this.envDownText+'</a></span>';
				} else if (this.osBrowser==2) {
					return '<span class="'+this.envInstallClass+'" style="text-align:center;"><a href="'+this.envPath+this.envSetupExe+'">'+this.envDownText+'</a></span>';
				} else {
					return '<div class="'+this.envInstallClass+'" style="text-align:center;">暂不支持此浏览器</div>';
				}
			},
			getObjectHtml: function(pgeId,sr) {
				if (this.osBrowser==1) {
					return '<span id="'+pgeId+'_pge" style="z-index:1;width:175px;background-color: white; height:20px;border:1px solid gray;padding-top:7px;" class="'+this.envClass+'">'
								+'<OBJECT ID="' + pgeId + '" codebase="SecEditCtl.YSB.x86.cab" CLASSID="CLSID:A791D428-6F85-47CD-B4D4-DF14E3FAE5DA" width="168" height="15" >' 
								+'<param name="ServerRandom" value="' + sr + '">'
								+'<param name="MinLength" value="8"/>'
								+'<param name="MaxLength" value="20"/>'
								+'<param name="IntensityRegExp" value="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$\"/>'
								+ '<param name="CipherType" value="0">'
								+'</OBJECT>'
								+'</span>'
				}  else if (this.osBrowser==2) {
					return '<span id="'+pgeId+'_pge" style="z-index:100;width:175px;background-color: white;border:1px solid gray;padding-top:7px;" class="'+this.envClass+'">'
							+'<OBJECT ID="' + pgeId + '" width="168" height="16" type="application/npSecEditCtl.YSB.x86" style="padding-top:3px;">'
							+ '<param name="ServerRandom" value="' + sr + '">'
							+'<param name="MinLength" value="8"/>'
							+'<param name="MaxLength" value="20"/>'
							+'<param name="IntensityRegExp" value="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$\"/>'
							+ '<param name="CipherType" value="0">'
							+'</OBJECT>'
							+'</span>';
				}  else if (this.osBrowser==3) {
					return '<span id="'+pgeId+'_pge" style="z-index:1;width:175px;background-color: white; height:20px;border:1px solid gray;padding-top:7px;" class="'+this.envClass+'">'
								+'<OBJECT ID="' + pgeId + '" codebase="SecEditCtl.YSB.x64.cab" CLASSID="CLSID:FB0DBE0E-1384-4E7C-A456-863EB0E9092A" width="168" height="15" >'
								+ '<param name="ServerRandom" value="' + sr + '">'
								+'<param name="MinLength" value="8"/>'
								+'<param name="MaxLength" value="20"/>'
								+'<param name="IntensityRegExp" value="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$\"/>'
								+ '<param name="CipherType" value="0">'
								+'</OBJECT>'
								+'</span>'				
				}else{
					return '';
				}
			},
			getHtml: function(pgeId) {
				if(shareServerRandom==''){
				$.ajax({//获取服务器端随机数
					  url: reqUrl+'/sr.do',
					  dataType: 'html',
					  async: false,
					  success: function(r){
						  sr = r;
						  shareServerRandom=r;
					  }
					});
				}else{
					sr=shareServerRandom;
				}
				this.isInstalled = this.getInstall();
				if (!this.isInstalled) {
					return this.getDownHtml();
				}else{
					if(this.envVersion!=SE_VERSION ){
						this.setDownText();
						return this.getDownHtml();
					}
					return this.getObjectHtml(pgeId,sr);
				}
			},
			setMaxLen: function(pgeId,maxLen) {		
				var obj = document.all(pgeId);
				if( obj != undefined )	obj.maxlen = parseInt(maxLen);
			},
			setSerialNo: function(pgeId,serial) {		
				if( serial == '' || serial == undefined ) serial = '1100000000000000';
				var obj = document.all(pgeId); 
				if(obj != undefined )obj.serialno = serial;
			},
			setEditorSize: function(pgeId,cx,cy) {		
				var obj = document.all(pgeId);
				if( cx == undefined || cx == "" ) cx = "0";
				if( cx == undefined || cy == "" ) cy = "0";
				obj.setEditorSize(parseInt(cx),parseInt(cy));
			},
//			setTabEnterCallback: function(pgeId,cb) {		
//				var obj = document.all(pgeId);
//				if( obj != undefined )	obj.SetJSCallback(cb);
//			},
			getResult: function(pgeId) {		
				var obj = document.all(pgeId); 
				return (obj != undefined) ? obj.scrkey : "";
			},
			isEmpty:function(pgeId){
				var obj=this.getResult(pgeId);
				var reg = /0{256}/;
				return reg.test(obj);
			},
			isResultEmpty:function(obj){
				var reg = /0{256}/;
				return reg.test(obj);
			}
		}
	);
	
	$.us.init();
	
})(jQuery);