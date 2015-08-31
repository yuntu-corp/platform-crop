<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- 标签库声明 --%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="uc" uri="/WEB-INF/tld/UC.tld"%>
<%-- 全局Java变量声明 --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- 页面变量声明 --%>
<c:set var="path" value="<%=path%>" />
<c:set var="basePath" value="<%=basePath%>" />

<!doctype html>
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi">
        <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi">
        
        <style>
		html,body,div,span,applet,object,iframe,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,dl,dt,dd,ol,nav ul,nav li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;vertical-align:baseline;}
article, aside, details, figcaption, figure,footer, header, hgroup, menu, nav, section {display: block;}
ol,ul{list-style:none;margin:0px;padding:0px;}
blockquote,q{quotes:none;}
blockquote:before,blockquote:after,q:before,q:after{content:'';content:none;}
table{border-collapse:collapse;border-spacing:0;}
*{ font-size:14px; color:#4c4c4c;}
/* start editing from here */
a{text-decoration:none;}


        .vote{ width:96%; margin:0 2% 0 2%;}
		.pic{ width:100%;}
		.content{ margin:4px 0; height:28px; overflow:hidden;}
        .name{ float:left; padding:2px 4px;}
		.vote_button{ float:right; background-image:url(${path }/module/vote/jsp/button.png); padding:2px 20px; color:#ff5a00; border:1px solid #fff; border-radius:4px;}
		.prize{ float:left; width:22%; margin:0 4px;}
		.prize p{ font-size:12px;}
		.ranking{ margin-bottom:4px;}
        </style>
        
        <script src="${path }/module/vote/jsp/jquery.js"></script>


<script type="text/javascript">
  (function() {
    WgateJs = {};
    WgateJs.auto_auth=true;
    var u=(("https:" == document.location.protocol) ? "https" : "http") + "://st.weixingate.com/";
    u=u+'st/1304';
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0]; g.type='text/javascript';
    g.defer=true; g.async=true; g.src=u; s.parentNode.insertBefore(g,s);
  })(); 
  
</script>

	<script type="text/javascript">
	
	var intervalLine = setInterval(function() {
		$("#text1").hide();
		$("#text2").hide();
		$("#text3").hide();
		//clearInterval(intervalLine);
}, 3000);

function submitVote(enrollIdStr){
	jQuery.ajax({
		url:"/platform/voteUser/voteOne",
		data:{enrollId:enrollIdStr,wgateId:WgateJs.getWgateid()},
		type:"post",
		async:false,
		success:function(data){
			if(data=="success"){
				$("#text1").show();
				intervalLinel;
			}else{
				$("#text2").show();
				intervalLinel;
			}
		}
	});
}
function gameOver(){
	$("#text3").show();
	intervalLinel;
}
</script>


</head>

<body>
	<!--标题-->
	<h1 style="font-size:18px; text-align:center;  color:#bf2e86;">梦幻尊园，包头首届薰衣草花海节</h1>

	<!--活动简介-->
	<div style="width: 94%; padding: 0 3%; margin: 4px 0;">
		宇宙之中,有一个人们没有发现的地方,它叫做梦幻尊园,那里的人们都会魔法,那是一个属于花的世界。那里有一个女神,名叫花海女神,就是在这样一个辽阔的地方,我们的故事就展开了……
	</div>


	<!--配图-->
	<div>
		<img src="${path }/module/vote/jsp/banner.png" style="width: 100%; border:none;">
		<img src="${path }/module/vote/jsp/picd.png" style="width:100%; border:none;">
		<img src="${path }/module/vote/jsp/pice.png" style="width:100%; border:none;">
	</div>

	<!--报名方式-->
        <div style="width:90%; margin:4px 2%; padding:6px; border:2px solid #781b74; ">
        	<p style="margin:6px 0;"><strong style="font-size:16px;color:#bf2e86;">报名方式:</strong></p>
        	<p>
            	<p style="color:#FF0004;">1、长按指纹关注“云图自媒体”微信公众号</p>
            	<p style="margin:8px 5%;text-align: center;">
                	<img src="${path }/module/vote/jsp/c.png" style="height:150px; ">
                	<%-- <img src="${path }/module/vote/jsp/b.png" style="height:120px;"> --%>
                </p>
                <p>><img src="${path }/module/vote/jsp/picf.png" style="width:100%;"> </P>
            </p>
        	
            <p>
            	<p style="color:#FF0004;">2、长按指纹关注“包头三江尊园”微信公众号</p>
            	<p style="margin:8px 5%;text-align: center;">
                	<img src="${path }/module/vote/jsp/a.png" style="height:150px;">
<%--                 	<img src="${path }/module/vote/jsp/b.png" style="height:120px;"> --%>
                </p>
            </p>

           <p style="margin-bottom:6px;color:#FF0004; ">
            	<p style="color:#FF0004;line-height:30px;">3、进入云图自媒体报名</p>
                <img src="${path }/module/vote/jsp/pica.png" style="width:100%;">
                <img src="${path }/module/vote/jsp/picb.png" style="width:100%;">
            </p>
            <p>
            	<p style="color:#FF0004; line-height:30px;">4、投票方式</p>
                <img src="${path }/module/vote/jsp/picc.png" style="width:100%;">
            </p>

        </div>

	<!--活动规则-->
	<div style="width:90%; margin:4px 2%; padding:6px; border:2px solid #781b74; ">
		<p style="margin:6px 0;"><strong style="font-size:16px;color:#bf2e86;">活动规则:</strong></p>
		<p>1、凡是莅临三江尊园的女性客户都可以参加，只要在薰衣草景点处，拍摄一张自拍照发送至三江尊园的微信公众平台既可以参加比赛。</p>
		<p>2、每位选手可以每天投一票给自己，也可以通过亲朋好友进行拉票，每位亲友每天只能投一次票。</p>
		<p>3、参赛年龄、人数、拍摄姿势均不限制，只要在三江尊园薰衣草景点内拍摄即可。</p>
		<p>4、根据参赛时间，每位选手均可以获得一个编号，根据编号既可投票。</p>
		<p></p>
	</div>


 <!--奖品-->
        <div style="width:90%; margin:4px 2%; padding:6px; border:2px solid #781b74; overflow:hidden; ">
        	<p style="margin:6px 0; "><strong style="font-size:16px;color:#bf2e86;">奖项及奖品:</strong></p>
            <p><img src="${path }/module/vote/jsp/prize.png" style="width:100%;"></p>
            <ul>
            	<li class="prize">
                	<p class="ranking"><strong style="font-size:11.5px; color:#FF0004;">一等奖&nbsp;1名</strong></p>
                    <p style="color:#ff5a00;">价值一万元的普罗旺斯游+一万元的购房代金券</p>
                <li class="prize">
                	<p class="ranking"><strong style="font-size:11.5px; color:#FF0004;">二等奖&nbsp;2名</strong></p>
                    <p style="color:#ff5a00;">价值两千元的iPad mini </p>
                <li class="prize">
                	<p class="ranking"><strong style="font-size:11.5px; color:#FF0004;">三等奖&nbsp;30名</strong></p>
                    <p style="color:#ff5a00;">价值500元的法国红酒一盒</p>
                <li class="prize">
                	<p class="ranking"><strong style="font-size:11.5px; color:#FF0004;">四等奖&nbsp;50名</strong></p>
                    <p style="color:#ff5a00;">价值100元的小米手环一个</p>
            </ul>
        </div>

	

	<!--活动时间-->
        <div style="width:90%; margin:4px 2%; padding:6px; border:2px solid #781b74; overflow:hidden; ">
        	<p style="margin:6px 0; "><strong style="font-size:16px;color:#bf2e86;">活动时间:</strong></p>
		<p>投票时间：8月8日开始——8月22日截止</p>
		<p>开奖时间：8月22日</p>
	</div>

	<!--图片-->
<!-- 	<div class="vote_container"> -->
<div>
		<c:forEach items="${enrollList}" var="enroll" varStatus="s">
			<div class="vote">
				<img src="${path }/${enroll.picUrl}" class="pic">
				<ul class="content">
					<%-- <li class="name">第${s.count}名</li> --%>
					<li class="name">${s.count}</li>
					<li class="name">${enroll.name}</li>
					<li class="name">${enroll.count}票</li>
					 <li><button class="vote_button" onclick="submitVote(${enroll.id+''})">投票</button> 
					<%--<li><button class="vote_button" onclick="gameOver()">投票</button>--%>
					<li>
				</ul>
			</div>
		</c:forEach>
	</div>

	<div id="text1" style="font-size:20px; background-color:#000; opacity:0.8; width:170px; text-align:center; border:1px solid #fff; border-radius:4px; line-height:36px; position:fixed; top:50%; left:25%; color:#FF2FBD; display:none;">投票成功！</div>
        <div id="text2" style="font-size:20px; background-color:#000; opacity:0.8; width:170px; text-align:center; border:1px solid #fff; border-radius:4px; line-height:36px; position:fixed; top:50%; left:25%; color:#FF2FBD; display:none;">您今天已投票！</div>
         <div id="text3" style="font-size:20px; background-color:#000; opacity:0.8; width:170px; text-align:center; border:1px solid #fff; border-radius:4px; line-height:36px; position:fixed; top:50%; left:25%; color:#FF2FBD; display:none;">活动已结束！</div>
<body>
</html>