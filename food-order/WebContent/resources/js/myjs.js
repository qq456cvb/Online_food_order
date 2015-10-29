lastScrollY=0;
function heartBeat(){
	var diffY;
	if (document.documentElement && document.documentElement.scrollTop) {
		diffY = document.documentElement.scrollTop;
	}
	else if (document.body) {
		diffY = document.body.scrollTop;
	}
	else
	{/*Netscape stuff*/}
	percent=.1*(diffY-lastScrollY);
	if(percent>0)	{
		percent=Math.ceil(percent);
	}
	else
	{
		percent=Math.floor(percent);
	}
	document.getElementById("full").style.top=parseInt(document.getElementById("full").style.top)+percent+"px";
	lastScrollY=lastScrollY+percent;
}
suspendcode="<div id=\"full\" style='right:10px;POSITION:absolute;TOP:800px;z-index:100' ><a onclick='window.scrollTo(0,0);'><span class='glyphicon glyphicon-arrow-up'></span>返回顶部</a><br></div>"
document.write(suspendcode);
window.setInterval("heartBeat()",1);