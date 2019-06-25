<ul class="pageSpite">
	<li><a href="javascript:goPage(${data.ds1page.prePage},${data.ds1page.pageCount})" class="before">&lt;</a></li>
	<li><a href="javascript:goPage(1,${data.ds1page.pageCount})" <#if '${data.ds1page.pageNo}' == '1'>class="page"<#else>class="otherpage"</#if>>1</a></li>
	<#if '${data.ds1page.pageCount}' != '1'>
	  <#if data.ds1page.pageNo-1 gt 1 && data.ds1page.pageNo-1 != 2>
	     <li><a href="javascript:void(0);" style="text-decoration: none;background:#373737" >...</a></li>
	  </#if>
	  <#if data.ds1page.pageNo-1 gt 1>
	    <li><a href="javascript:goPage(${data.ds1page.pageNo-1},${data.ds1page.pageCount})"  class="otherpage">${data.ds1page.pageNo-1}</a></li>
	  </#if>
	  <#if data.ds1page.pageNo != 1 && data.ds1page.pageNo != data.ds1page.pageCount>
	    <li><a href="javascript:goPage(${data.ds1page.pageNo},${data.ds1page.pageCount})" class="page">${data.ds1page.pageNo}</a></li>
	  </#if>
	  <#if data.ds1page.pageNo+1 lt data.ds1page.pageCount>
	    <li><a href="javascript:goPage(${data.ds1page.pageNo+1},${data.ds1page.pageCount})" class="otherpage" >${data.ds1page.pageNo+1}</a></li>
	  </#if>
	  <#if data.ds1page.pageNo+1 lt data.ds1page.pageCount && data.ds1page.pageNo+1 != data.ds1page.pageCount-1>
	    <li><a href="javascript:void(0);" style="text-decoration: none;">...</a></li>
	  </#if>
	  <li><a href="javascript:goPage(${data.ds1page.pageCount},${data.ds1page.pageCount})" <#if '${data.ds1page.pageNo}' == '${data.ds1page.pageCount}'> class="page"<#else>class="otherpage"</#if>>${data.ds1page.pageCount}</a></li>
	</#if>
	<li><a href="javascript:goPage(${data.ds1page.nextPage},${data.ds1page.pageCount})" class="next">&gt;</a></li>
	<span class="dataAll">
	 	共有<span class="a" >${data.ds1page.rowCount.toString()}</span>条数据，每页<span class="b" >${data.ds1page.pageSize}</span>条,到第
	  <input id="pageint" name="" type="text" class="input" value="${data.ds1page.pageNo}">页</span>	
	  <a class="sure" href="#" onclick="skipPage(${data.ds1page.pageCount.toString()});">确定</a>
	</span>	
	<script>
	  $(function(){
	  $('li a').on('click',function(){
	   $(this).addClass('active').siblings('li a').removeClass('li a');
	  })
	  })
	 </script>	
</ul>		
