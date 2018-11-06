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

public final class icons_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_icon_symbol_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_icon_symbol_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_icon_symbol_nobody.release();
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

      out.write("\n\n<blockquote><p>Icon is a visual metaphor representing a concept that lies behind the idea and/or action.</p></blockquote>\n\n<h3>Liferay Icon Library</h3>\n\n<div class=\"mb-3 row\">\n\n\t");

	String[] icons = {"add-cell", "add-column", "add-role", "add-row", "adjust", "align-center", "align-justify", "align-left", "align-right", "angle-down", "angle-left", "angle-right", "angle-up", "archive", "asterisk", "audio", "autosize", "bars", "blogs", "bold", "bookmarks", "calendar", "camera", "cards", "cards2", "caret-bottom", "caret-double-l", "caret-double", "caret-top", "categories", "chain-broken", "change", "check-circle", "check", "code", "cog", "columns", "comments", "compress", "control-panel", "custom-size", "cut", "date", "desktop", "documents-and-media", "download", "dynamic-data-list", "edit-layout", "effects", "ellipsis-h", "ellipsis-v", "embed", "envelope-closed", "envelope-open", "exclamation-circle", "exclamation-full", "expand", "flag-empty", "flag-full", "folder", "format", "forms", "full-size", "geolocation", "grid", "h1", "h2", "heart", "hidden", "home", "horizontal-scroll", "hr", "import-export", "indent-less", "indent-more", "info-circle-open", "info-circle", "info-panel-closed", "info-panel-open", "information-live", "italic", "link", "list-ol", "list-ul", "list", "live", "lock", "logout", "magic", "mark-as-answer", "mark-as-question", "message-boards", "mobile-device-rules", "mobile-landscape", "mobile-portrait", "moon", "move", "myspace", "order-arrow", "organizations", "page-template", "page", "pages-tree", "paperclip", "paragraph", "password-policies", "paste", "pause", "pencil", "phone", "picture", "play", "plus", "polls", "print", "product-menu-closed", "product-menu-open", "product-menu", "question-circle-full", "question-circle", "quote-left", "quote-right", "radio-button", "redo", "reload", "remove-role", "remove-style", "reply", "repository", "restore", "rss", "search", "select-from-list", "share-alt", "share", "shortcut", "simulation-menu-closed", "simulation-menu-open", "simulation-menu", "site-template", "sites", "staging", "star-half", "star-o", "star", "strikethrough", "subscript", "suitcase", "sun", "superscript", "table", "table2", "tablet-landscape", "tablet-portrait", "tag", "text-editor", "text", "thumbs-down", "thumbs-up", "time", "times-circle", "times", "transform", "trash", "twitter", "underline", "undo", "unlock", "user", "users", "vertical-scroll", "view", "vocabulary", "web-content", "wiki-page", "wiki", "workflow"};

	for (int i = 0; i < icons.length; i++) {
	
      out.write("\n\n\t\t<div class=\"col-md-3\">\n\t\t\t");
      //  clay:icon
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.IconTag _jspx_th_clay_icon_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.IconTag) _jspx_tagPool_clay_icon_symbol_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.IconTag.class);
      _jspx_th_clay_icon_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_icon_0.setParent(null);
      _jspx_th_clay_icon_0.setSymbol( icons[i] );
      int _jspx_eval_clay_icon_0 = _jspx_th_clay_icon_0.doStartTag();
      if (_jspx_th_clay_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_icon_symbol_nobody.reuse(_jspx_th_clay_icon_0);
        return;
      }
      _jspx_tagPool_clay_icon_symbol_nobody.reuse(_jspx_th_clay_icon_0);
      out.write("\n\n\t\t\t<span class=\"ml-2\">");
      out.print( icons[i] );
      out.write("</span>\n\t\t</div>\n\n\t");

	}
	
      out.write("\n\n</div>\n\n<h3>Language Flags</h3>\n\n<div class=\"mb-3 row\">\n\n\t");

	String[] flags = {"ar-sa", "bg-bg", "ca-ad", "ca-es", "cs-cz", "da-dk", "de-de", "el-gr", "en-au", "en-gb", "en-us", "es-es", "et-ee", "eu-es", "fa-ir", "fi-fi", "fr-ca", "fr-fr", "gl-es", "hi-in", "hr-hr", "hu-hu", "in-id", "it-it", "iw-il", "ja-jp", "ko-kr", "lo-la", "lt-lt", "nb-no", "nl-be", "nl-nl", "pl-pl", "pt-br", "pt-pt", "ro-ro", "ru-ru", "sk-sk", "sl-si", "sr-rs-latin", "sr-rs", "sv-se", "th-th", "tr-tr", "uk-ua", "vi-vn", "zh-cn", "zh-tw"};

	for (int i = 0; i < flags.length; i++) {
	
      out.write("\n\n\t\t<div class=\"col-md-3\">\n\t\t\t");
      //  clay:icon
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.IconTag _jspx_th_clay_icon_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.IconTag) _jspx_tagPool_clay_icon_symbol_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.IconTag.class);
      _jspx_th_clay_icon_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_icon_1.setParent(null);
      _jspx_th_clay_icon_1.setSymbol( flags[i] );
      int _jspx_eval_clay_icon_1 = _jspx_th_clay_icon_1.doStartTag();
      if (_jspx_th_clay_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_icon_symbol_nobody.reuse(_jspx_th_clay_icon_1);
        return;
      }
      _jspx_tagPool_clay_icon_symbol_nobody.reuse(_jspx_th_clay_icon_1);
      out.write("\n\n\t\t\t<span class=\"ml-2\">");
      out.print( flags[i] );
      out.write("</span>\n\t\t</div>\n\n\t");

	}
	
      out.write("\n\n</div>");
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
