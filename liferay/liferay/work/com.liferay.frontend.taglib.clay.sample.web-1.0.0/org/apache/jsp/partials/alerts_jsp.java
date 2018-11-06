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

public final class alerts_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_stripe_title_style_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_alert_title_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_stripe_title_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_alert_title_style_message_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_stripe_title_style_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_alert_title_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_stripe_title_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_alert_title_style_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_stripe_title_style_message_nobody.release();
    _jspx_tagPool_clay_alert_title_message_nobody.release();
    _jspx_tagPool_clay_stripe_title_message_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_alert_title_style_message_nobody.release();
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

      out.write("\n\n<h3>EMBEDDED</h3>\n\n<blockquote><p>Embedded alerts are thought to be used inside context as forms. Usually you will only need to use the information one. Its width depends on the container with you use it, always respecting the container margins to the content. The close action is not of mandatory use.</p></blockquote>\n\n");
      if (_jspx_meth_clay_alert_0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_clay_alert_1(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_clay_alert_2(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_clay_alert_3(_jspx_page_context))
        return;
      out.write("\n\n<h3>STRIPE</h3>\n\n<blockquote><p>Stripe alerts are always placed below the last navigation element, either the header or the navigation bar. This alert appears usually on \"Save\" action communicating the status of the action once received from the server. The close action is mandatory in this alert type. Its width is always full container width and pushes all the content below it.</p></blockquote>\n\n");
      if (_jspx_meth_clay_stripe_0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_clay_stripe_1(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_clay_stripe_2(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_clay_stripe_3(_jspx_page_context))
        return;
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

  private boolean _jspx_meth_clay_alert_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:alert
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag _jspx_th_clay_alert_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag) _jspx_tagPool_clay_alert_title_style_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag.class);
    _jspx_th_clay_alert_0.setPageContext(_jspx_page_context);
    _jspx_th_clay_alert_0.setParent(null);
    _jspx_th_clay_alert_0.setMessage("This is an error message.");
    _jspx_th_clay_alert_0.setStyle("danger");
    _jspx_th_clay_alert_0.setTitle("Error");
    int _jspx_eval_clay_alert_0 = _jspx_th_clay_alert_0.doStartTag();
    if (_jspx_th_clay_alert_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_alert_title_style_message_nobody.reuse(_jspx_th_clay_alert_0);
      return true;
    }
    _jspx_tagPool_clay_alert_title_style_message_nobody.reuse(_jspx_th_clay_alert_0);
    return false;
  }

  private boolean _jspx_meth_clay_alert_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:alert
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag _jspx_th_clay_alert_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag) _jspx_tagPool_clay_alert_title_style_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag.class);
    _jspx_th_clay_alert_1.setPageContext(_jspx_page_context);
    _jspx_th_clay_alert_1.setParent(null);
    _jspx_th_clay_alert_1.setMessage("This is a success message.");
    _jspx_th_clay_alert_1.setStyle("success");
    _jspx_th_clay_alert_1.setTitle("Success");
    int _jspx_eval_clay_alert_1 = _jspx_th_clay_alert_1.doStartTag();
    if (_jspx_th_clay_alert_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_alert_title_style_message_nobody.reuse(_jspx_th_clay_alert_1);
      return true;
    }
    _jspx_tagPool_clay_alert_title_style_message_nobody.reuse(_jspx_th_clay_alert_1);
    return false;
  }

  private boolean _jspx_meth_clay_alert_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:alert
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag _jspx_th_clay_alert_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag) _jspx_tagPool_clay_alert_title_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag.class);
    _jspx_th_clay_alert_2.setPageContext(_jspx_page_context);
    _jspx_th_clay_alert_2.setParent(null);
    _jspx_th_clay_alert_2.setMessage("This is an info message.");
    _jspx_th_clay_alert_2.setTitle("Info");
    int _jspx_eval_clay_alert_2 = _jspx_th_clay_alert_2.doStartTag();
    if (_jspx_th_clay_alert_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_alert_title_message_nobody.reuse(_jspx_th_clay_alert_2);
      return true;
    }
    _jspx_tagPool_clay_alert_title_message_nobody.reuse(_jspx_th_clay_alert_2);
    return false;
  }

  private boolean _jspx_meth_clay_alert_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:alert
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag _jspx_th_clay_alert_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag) _jspx_tagPool_clay_alert_title_style_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.AlertTag.class);
    _jspx_th_clay_alert_3.setPageContext(_jspx_page_context);
    _jspx_th_clay_alert_3.setParent(null);
    _jspx_th_clay_alert_3.setMessage("This is a warning message.");
    _jspx_th_clay_alert_3.setStyle("warning");
    _jspx_th_clay_alert_3.setTitle("Warning");
    int _jspx_eval_clay_alert_3 = _jspx_th_clay_alert_3.doStartTag();
    if (_jspx_th_clay_alert_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_alert_title_style_message_nobody.reuse(_jspx_th_clay_alert_3);
      return true;
    }
    _jspx_tagPool_clay_alert_title_style_message_nobody.reuse(_jspx_th_clay_alert_3);
    return false;
  }

  private boolean _jspx_meth_clay_stripe_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:stripe
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag _jspx_th_clay_stripe_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag) _jspx_tagPool_clay_stripe_title_style_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag.class);
    _jspx_th_clay_stripe_0.setPageContext(_jspx_page_context);
    _jspx_th_clay_stripe_0.setParent(null);
    _jspx_th_clay_stripe_0.setMessage("This is an error message.");
    _jspx_th_clay_stripe_0.setStyle("danger");
    _jspx_th_clay_stripe_0.setTitle("Error");
    int _jspx_eval_clay_stripe_0 = _jspx_th_clay_stripe_0.doStartTag();
    if (_jspx_th_clay_stripe_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_stripe_title_style_message_nobody.reuse(_jspx_th_clay_stripe_0);
      return true;
    }
    _jspx_tagPool_clay_stripe_title_style_message_nobody.reuse(_jspx_th_clay_stripe_0);
    return false;
  }

  private boolean _jspx_meth_clay_stripe_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:stripe
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag _jspx_th_clay_stripe_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag) _jspx_tagPool_clay_stripe_title_style_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag.class);
    _jspx_th_clay_stripe_1.setPageContext(_jspx_page_context);
    _jspx_th_clay_stripe_1.setParent(null);
    _jspx_th_clay_stripe_1.setMessage("This is a success message.");
    _jspx_th_clay_stripe_1.setStyle("success");
    _jspx_th_clay_stripe_1.setTitle("Success");
    int _jspx_eval_clay_stripe_1 = _jspx_th_clay_stripe_1.doStartTag();
    if (_jspx_th_clay_stripe_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_stripe_title_style_message_nobody.reuse(_jspx_th_clay_stripe_1);
      return true;
    }
    _jspx_tagPool_clay_stripe_title_style_message_nobody.reuse(_jspx_th_clay_stripe_1);
    return false;
  }

  private boolean _jspx_meth_clay_stripe_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:stripe
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag _jspx_th_clay_stripe_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag) _jspx_tagPool_clay_stripe_title_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag.class);
    _jspx_th_clay_stripe_2.setPageContext(_jspx_page_context);
    _jspx_th_clay_stripe_2.setParent(null);
    _jspx_th_clay_stripe_2.setMessage("This is an info message.");
    _jspx_th_clay_stripe_2.setTitle("Info");
    int _jspx_eval_clay_stripe_2 = _jspx_th_clay_stripe_2.doStartTag();
    if (_jspx_th_clay_stripe_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_stripe_title_message_nobody.reuse(_jspx_th_clay_stripe_2);
      return true;
    }
    _jspx_tagPool_clay_stripe_title_message_nobody.reuse(_jspx_th_clay_stripe_2);
    return false;
  }

  private boolean _jspx_meth_clay_stripe_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:stripe
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag _jspx_th_clay_stripe_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag) _jspx_tagPool_clay_stripe_title_style_message_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.StripeTag.class);
    _jspx_th_clay_stripe_3.setPageContext(_jspx_page_context);
    _jspx_th_clay_stripe_3.setParent(null);
    _jspx_th_clay_stripe_3.setMessage("This is a warning message.");
    _jspx_th_clay_stripe_3.setStyle("warning");
    _jspx_th_clay_stripe_3.setTitle("Warning");
    int _jspx_eval_clay_stripe_3 = _jspx_th_clay_stripe_3.doStartTag();
    if (_jspx_th_clay_stripe_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_stripe_title_style_message_nobody.reuse(_jspx_th_clay_stripe_3);
      return true;
    }
    _jspx_tagPool_clay_stripe_title_style_message_nobody.reuse(_jspx_th_clay_stripe_3);
    return false;
  }
}
