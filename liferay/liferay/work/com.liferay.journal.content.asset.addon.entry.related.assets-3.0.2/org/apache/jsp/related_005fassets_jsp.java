package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.portal.kernel.util.WebKeys;

public final class related_005fassets_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n\n\n\n\n\n");

JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);

      out.write("\n\n<div class=\"content-metadata-asset-addon-entry content-metadata-asset-addon-entry-links\">\n\t");
      //  liferay-asset:asset-links
      com.liferay.asset.taglib.servlet.taglib.AssetLinksTag _jspx_th_liferay$1asset_asset$1links_0 = (com.liferay.asset.taglib.servlet.taglib.AssetLinksTag) _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetLinksTag.class);
      _jspx_th_liferay$1asset_asset$1links_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1asset_asset$1links_0.setParent(null);
      _jspx_th_liferay$1asset_asset$1links_0.setClassName( JournalArticle.class.getName() );
      _jspx_th_liferay$1asset_asset$1links_0.setClassPK( articleDisplay.getResourcePrimKey() );
      int _jspx_eval_liferay$1asset_asset$1links_0 = _jspx_th_liferay$1asset_asset$1links_0.doStartTag();
      if (_jspx_th_liferay$1asset_asset$1links_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1links_0);
        return;
      }
      _jspx_tagPool_liferay$1asset_asset$1links_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1links_0);
      out.write("\n</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
