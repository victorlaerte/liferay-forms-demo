package org.apache.jsp.partials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.taglib.clay.sample.web.constants.ClaySamplePortletKeys;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.CardsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.DropdownsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.ManagementToolbarsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.NavigationBarsDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class badges_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_badge_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_badge_style_label_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_badge_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_badge_style_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_badge_label_nobody.release();
    _jspx_tagPool_clay_badge_style_label_nobody.release();
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

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n");
      //  liferay-theme:defineObjects
      com.liferay.taglib.theme.DefineObjectsTag _jspx_th_liferay$1theme_defineObjects_0 = (com.liferay.taglib.theme.DefineObjectsTag) _jspx_tagPool_liferay$1theme_defineObjects_nobody.get(com.liferay.taglib.theme.DefineObjectsTag.class);
      _jspx_th_liferay$1theme_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1theme_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1theme_defineObjects_0 = _jspx_th_liferay$1theme_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1theme_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
      com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay = null;
      com.liferay.portal.kernel.model.Company company = null;
      com.liferay.portal.kernel.model.Account account = null;
      com.liferay.portal.kernel.model.User user = null;
      com.liferay.portal.kernel.model.User realUser = null;
      com.liferay.portal.kernel.model.Contact contact = null;
      com.liferay.portal.kernel.model.Layout layout = null;
      java.util.List layouts = null;
      java.lang.Long plid = null;
      com.liferay.portal.kernel.model.LayoutTypePortlet layoutTypePortlet = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker = null;
      java.util.Locale locale = null;
      java.util.TimeZone timeZone = null;
      com.liferay.portal.kernel.model.Theme theme = null;
      com.liferay.portal.kernel.model.ColorScheme colorScheme = null;
      com.liferay.portal.kernel.theme.PortletDisplay portletDisplay = null;
      java.lang.Long portletGroupId = null;
      themeDisplay = (com.liferay.portal.kernel.theme.ThemeDisplay) _jspx_page_context.findAttribute("themeDisplay");
      company = (com.liferay.portal.kernel.model.Company) _jspx_page_context.findAttribute("company");
      account = (com.liferay.portal.kernel.model.Account) _jspx_page_context.findAttribute("account");
      user = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("user");
      realUser = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("realUser");
      contact = (com.liferay.portal.kernel.model.Contact) _jspx_page_context.findAttribute("contact");
      layout = (com.liferay.portal.kernel.model.Layout) _jspx_page_context.findAttribute("layout");
      layouts = (java.util.List) _jspx_page_context.findAttribute("layouts");
      plid = (java.lang.Long) _jspx_page_context.findAttribute("plid");
      layoutTypePortlet = (com.liferay.portal.kernel.model.LayoutTypePortlet) _jspx_page_context.findAttribute("layoutTypePortlet");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      permissionChecker = (com.liferay.portal.kernel.security.permission.PermissionChecker) _jspx_page_context.findAttribute("permissionChecker");
      locale = (java.util.Locale) _jspx_page_context.findAttribute("locale");
      timeZone = (java.util.TimeZone) _jspx_page_context.findAttribute("timeZone");
      theme = (com.liferay.portal.kernel.model.Theme) _jspx_page_context.findAttribute("theme");
      colorScheme = (com.liferay.portal.kernel.model.ColorScheme) _jspx_page_context.findAttribute("colorScheme");
      portletDisplay = (com.liferay.portal.kernel.theme.PortletDisplay) _jspx_page_context.findAttribute("portletDisplay");
      portletGroupId = (java.lang.Long) _jspx_page_context.findAttribute("portletGroupId");
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

CardsDisplayContext cardsDisplayContext = (CardsDisplayContext)request.getAttribute(ClaySamplePortletKeys.CARDS_DISPLAY_CONTEXT);
DropdownsDisplayContext dropdownsDisplayContext = (DropdownsDisplayContext)request.getAttribute(ClaySamplePortletKeys.DROPDOWNS_DISPLAY_CONTEXT);
ManagementToolbarsDisplayContext managementToolbarsDisplayContext = (ManagementToolbarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.MANAGEMENT_TOOLBARS_DISPLAY_CONTEXT);
NavigationBarsDisplayContext navigationBarsDisplayContext = (NavigationBarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.NAVIGATION_BARS_DISPLAY_CONTEXT);

      out.write("\n\n<blockquote><p>Badges help highlight important information such as notifications or new and unread messages. Badges have circular borders and are only used to specify a number.</p></blockquote>\n\n<div class=\"row text-center\">\n\t<div class=\"col-md-1\">\n\t\t");
      if (_jspx_meth_clay_badge_0(_jspx_page_context))
        return;
      out.write("\n\n\t\t<div>Primary</div>\n\t</div>\n\n\t<div class=\"col-md-1\">\n\t\t");
      if (_jspx_meth_clay_badge_1(_jspx_page_context))
        return;
      out.write("\n\n\t\t<div>Secondary</div>\n\t</div>\n\n\t<div class=\"col-md-1\">\n\t\t");
      if (_jspx_meth_clay_badge_2(_jspx_page_context))
        return;
      out.write("\n\n\t\t<div>Info</div>\n\t</div>\n\n\t<div class=\"col-md-1\">\n\t\t");
      if (_jspx_meth_clay_badge_3(_jspx_page_context))
        return;
      out.write("\n\n\t\t<div>Error</div>\n\t</div>\n\n\t<div class=\"col-md-1\">\n\t\t");
      if (_jspx_meth_clay_badge_4(_jspx_page_context))
        return;
      out.write("\n\n\t\t<div>Success</div>\n\t</div>\n\n\t<div class=\"col-md-1\">\n\t\t");
      if (_jspx_meth_clay_badge_5(_jspx_page_context))
        return;
      out.write("\n\n\t\t<div>Warning</div>\n\t</div>\n</div>");
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

  private boolean _jspx_meth_clay_badge_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:badge
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag _jspx_th_clay_badge_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag) _jspx_tagPool_clay_badge_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag.class);
    _jspx_th_clay_badge_0.setPageContext(_jspx_page_context);
    _jspx_th_clay_badge_0.setParent(null);
    _jspx_th_clay_badge_0.setLabel("8");
    int _jspx_eval_clay_badge_0 = _jspx_th_clay_badge_0.doStartTag();
    if (_jspx_th_clay_badge_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_badge_label_nobody.reuse(_jspx_th_clay_badge_0);
      return true;
    }
    _jspx_tagPool_clay_badge_label_nobody.reuse(_jspx_th_clay_badge_0);
    return false;
  }

  private boolean _jspx_meth_clay_badge_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:badge
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag _jspx_th_clay_badge_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag) _jspx_tagPool_clay_badge_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag.class);
    _jspx_th_clay_badge_1.setPageContext(_jspx_page_context);
    _jspx_th_clay_badge_1.setParent(null);
    _jspx_th_clay_badge_1.setLabel("87");
    _jspx_th_clay_badge_1.setStyle("secondary");
    int _jspx_eval_clay_badge_1 = _jspx_th_clay_badge_1.doStartTag();
    if (_jspx_th_clay_badge_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_1);
      return true;
    }
    _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_1);
    return false;
  }

  private boolean _jspx_meth_clay_badge_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:badge
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag _jspx_th_clay_badge_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag) _jspx_tagPool_clay_badge_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag.class);
    _jspx_th_clay_badge_2.setPageContext(_jspx_page_context);
    _jspx_th_clay_badge_2.setParent(null);
    _jspx_th_clay_badge_2.setLabel("91");
    _jspx_th_clay_badge_2.setStyle("info");
    int _jspx_eval_clay_badge_2 = _jspx_th_clay_badge_2.doStartTag();
    if (_jspx_th_clay_badge_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_2);
      return true;
    }
    _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_2);
    return false;
  }

  private boolean _jspx_meth_clay_badge_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:badge
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag _jspx_th_clay_badge_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag) _jspx_tagPool_clay_badge_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag.class);
    _jspx_th_clay_badge_3.setPageContext(_jspx_page_context);
    _jspx_th_clay_badge_3.setParent(null);
    _jspx_th_clay_badge_3.setLabel("130");
    _jspx_th_clay_badge_3.setStyle("danger");
    int _jspx_eval_clay_badge_3 = _jspx_th_clay_badge_3.doStartTag();
    if (_jspx_th_clay_badge_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_3);
      return true;
    }
    _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_3);
    return false;
  }

  private boolean _jspx_meth_clay_badge_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:badge
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag _jspx_th_clay_badge_4 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag) _jspx_tagPool_clay_badge_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag.class);
    _jspx_th_clay_badge_4.setPageContext(_jspx_page_context);
    _jspx_th_clay_badge_4.setParent(null);
    _jspx_th_clay_badge_4.setLabel("1111");
    _jspx_th_clay_badge_4.setStyle("success");
    int _jspx_eval_clay_badge_4 = _jspx_th_clay_badge_4.doStartTag();
    if (_jspx_th_clay_badge_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_4);
      return true;
    }
    _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_4);
    return false;
  }

  private boolean _jspx_meth_clay_badge_5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:badge
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag _jspx_th_clay_badge_5 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag) _jspx_tagPool_clay_badge_style_label_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.BadgeTag.class);
    _jspx_th_clay_badge_5.setPageContext(_jspx_page_context);
    _jspx_th_clay_badge_5.setParent(null);
    _jspx_th_clay_badge_5.setLabel("21");
    _jspx_th_clay_badge_5.setStyle("warning");
    int _jspx_eval_clay_badge_5 = _jspx_th_clay_badge_5.doStartTag();
    if (_jspx_th_clay_badge_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_5);
      return true;
    }
    _jspx_tagPool_clay_badge_style_label_nobody.reuse(_jspx_th_clay_badge_5);
    return false;
  }
}
