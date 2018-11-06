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

public final class cards_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_imageSrc_imageAlt_href_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_user$1card_subtitle_selected_selectable_name_imageSrc_imageAlt_disabled_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_icon_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_icon_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_labelStylesMap_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_icon_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_user$1card_userColor_subtitle_name_initials_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_horizontal$1card_title_selected_selectable_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_horizontal$1card_title_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_labelStylesMap_icon_actionDropdownItems_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_image$1card_title_subtitle_imageSrc_imageAlt_href_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_user$1card_subtitle_selected_selectable_name_imageSrc_imageAlt_disabled_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_icon_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_icon_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_labelStylesMap_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_icon_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_user$1card_userColor_subtitle_name_initials_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_horizontal$1card_title_selected_selectable_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_horizontal$1card_title_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_labelStylesMap_icon_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_image$1card_title_subtitle_imageSrc_imageAlt_href_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_user$1card_subtitle_selected_selectable_name_imageSrc_imageAlt_disabled_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_icon_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_icon_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_labelStylesMap_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_icon_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_user$1card_userColor_subtitle_name_initials_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_horizontal$1card_title_selected_selectable_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_horizontal$1card_title_nobody.release();
    _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_labelStylesMap_icon_actionDropdownItems_nobody.release();
    _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.release();
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

      out.write("\n\n<h3>Image cards</h3>\n\n");

String DOC_FILE_TITLE = "deliverable.doc";
String MP3_FILE_TITLE = "deliverable.mp3";
String PDF_FILE_TITLE = "deliverable.pdf";
String PNG_FILE_TITLE = "lexicon.icon.camera.png";
String SVG_FILE_TITLE = "lexicon.icon.camera.svg";

      out.write("\n\n<div class=\"row\">\n\t<div class=\"col-md-4\" id=\"image-card-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_imageSrc_imageAlt_href_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_0.setParent(null);
      _jspx_th_clay_image$1card_0.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_0.setHref("#1");
      _jspx_th_clay_image$1card_0.setImageAlt("thumbnail");
      _jspx_th_clay_image$1card_0.setImageSrc("https://images.unsplash.com/photo-1506976773555-b3da30a63b57");
      _jspx_th_clay_image$1card_0.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_0.setTitle("Madrid");
      int _jspx_eval_clay_image$1card_0 = _jspx_th_clay_image$1card_0.doStartTag();
      if (_jspx_th_clay_image$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_0);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_0);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_icon_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_1.setParent(null);
      _jspx_th_clay_image$1card_1.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_1.setIcon("camera");
      _jspx_th_clay_image$1card_1.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_1.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_1 = _jspx_th_clay_image$1card_1.doStartTag();
      if (_jspx_th_clay_image$1card_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_1);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_1);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-empty-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_2.setParent(null);
      _jspx_th_clay_image$1card_2.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_2.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_2.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_2 = _jspx_th_clay_image$1card_2.doStartTag();
      if (_jspx_th_clay_image$1card_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_2);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_2);
      out.write("\n\t</div>\n</div>\n\n<h4>Image Card with Sticker</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-4\" id=\"image-card-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_3 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_3.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_3.setParent(null);
      _jspx_th_clay_image$1card_3.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_3.setHref("#1");
      _jspx_th_clay_image$1card_3.setImageAlt("thumbnail");
      _jspx_th_clay_image$1card_3.setImageSrc("https://images.unsplash.com/photo-1499310226026-b9d598980b90");
      _jspx_th_clay_image$1card_3.setStickerLabel("JPG");
      _jspx_th_clay_image$1card_3.setStickerStyle("danger");
      _jspx_th_clay_image$1card_3.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_3.setTitle("California");
      int _jspx_eval_clay_image$1card_3 = _jspx_th_clay_image$1card_3.doStartTag();
      if (_jspx_th_clay_image$1card_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_3);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_3);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_4 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_icon_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_4.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_4.setParent(null);
      _jspx_th_clay_image$1card_4.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_4.setIcon("camera");
      _jspx_th_clay_image$1card_4.setStickerLabel("SVG");
      _jspx_th_clay_image$1card_4.setStickerStyle("warning");
      _jspx_th_clay_image$1card_4.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_4.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_4 = _jspx_th_clay_image$1card_4.doStartTag();
      if (_jspx_th_clay_image$1card_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_4);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_4);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-empty-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_5 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_5.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_5.setParent(null);
      _jspx_th_clay_image$1card_5.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_5.setStickerLabel("PNG");
      _jspx_th_clay_image$1card_5.setStickerStyle("info");
      _jspx_th_clay_image$1card_5.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_5.setTitle( PNG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_5 = _jspx_th_clay_image$1card_5.doStartTag();
      if (_jspx_th_clay_image$1card_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_5);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_5);
      out.write("\n\t</div>\n</div>\n\n<h4>Image Card with Sticker Shape</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-4\" id=\"image-card-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_6 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_6.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_6.setParent(null);
      _jspx_th_clay_image$1card_6.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_6.setHref("#1");
      _jspx_th_clay_image$1card_6.setImageAlt("thumbnail");
      _jspx_th_clay_image$1card_6.setImageSrc("https://images.unsplash.com/photo-1490900245048-1bf948e866c2");
      _jspx_th_clay_image$1card_6.setStickerLabel("JPG");
      _jspx_th_clay_image$1card_6.setStickerShape("circle");
      _jspx_th_clay_image$1card_6.setStickerStyle("danger");
      _jspx_th_clay_image$1card_6.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_6.setTitle("California");
      int _jspx_eval_clay_image$1card_6 = _jspx_th_clay_image$1card_6.doStartTag();
      if (_jspx_th_clay_image$1card_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_6);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_6);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_7 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_icon_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_7.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_7.setParent(null);
      _jspx_th_clay_image$1card_7.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_7.setIcon("camera");
      _jspx_th_clay_image$1card_7.setStickerLabel("SVG");
      _jspx_th_clay_image$1card_7.setStickerShape("circle");
      _jspx_th_clay_image$1card_7.setStickerStyle("warning");
      _jspx_th_clay_image$1card_7.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_7.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_7 = _jspx_th_clay_image$1card_7.doStartTag();
      if (_jspx_th_clay_image$1card_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_7);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_7);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-empty-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_8 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_8.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_8.setParent(null);
      _jspx_th_clay_image$1card_8.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_8.setStickerLabel("PNG");
      _jspx_th_clay_image$1card_8.setStickerShape("circle");
      _jspx_th_clay_image$1card_8.setStickerStyle("info");
      _jspx_th_clay_image$1card_8.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_8.setTitle( PNG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_8 = _jspx_th_clay_image$1card_8.doStartTag();
      if (_jspx_th_clay_image$1card_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_8);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerShape_stickerLabel_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_8);
      out.write("\n\t</div>\n</div>\n\n<h4>Image Card with Labels</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-4\" id=\"image-card-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_9 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_9.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_9.setParent(null);
      _jspx_th_clay_image$1card_9.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_9.setHref("#1");
      _jspx_th_clay_image$1card_9.setImageAlt("thumbnail");
      _jspx_th_clay_image$1card_9.setImageSrc("https://images.unsplash.com/photo-1503703294279-c83bdf7b4bf4");
      _jspx_th_clay_image$1card_9.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_image$1card_9.setStickerLabel("JPG");
      _jspx_th_clay_image$1card_9.setStickerStyle("danger");
      _jspx_th_clay_image$1card_9.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_9.setTitle("Beetle");
      int _jspx_eval_clay_image$1card_9 = _jspx_th_clay_image$1card_9.doStartTag();
      if (_jspx_th_clay_image$1card_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_9);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_9);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_10 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_labelStylesMap_icon_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_10.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_10.setParent(null);
      _jspx_th_clay_image$1card_10.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_10.setIcon("camera");
      _jspx_th_clay_image$1card_10.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_image$1card_10.setLabelStylesMap( cardsDisplayContext.getLabelStylesMap() );
      _jspx_th_clay_image$1card_10.setStickerLabel("SVG");
      _jspx_th_clay_image$1card_10.setStickerStyle("warning");
      _jspx_th_clay_image$1card_10.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_10.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_10 = _jspx_th_clay_image$1card_10.doStartTag();
      if (_jspx_th_clay_image$1card_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_labelStylesMap_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_10);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_labelStylesMap_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_10);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-empty-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_11 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_11.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_11.setParent(null);
      _jspx_th_clay_image$1card_11.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_11.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_image$1card_11.setStickerLabel("PNG");
      _jspx_th_clay_image$1card_11.setStickerStyle("info");
      _jspx_th_clay_image$1card_11.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_11.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_11 = _jspx_th_clay_image$1card_11.doStartTag();
      if (_jspx_th_clay_image$1card_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_11);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_11);
      out.write("\n\t</div>\n</div>\n\n<h4>Selectable Image Card</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-4\" id=\"image-card-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_12 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_12.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_12.setParent(null);
      _jspx_th_clay_image$1card_12.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_12.setHref("#1");
      _jspx_th_clay_image$1card_12.setImageAlt("thumbnail");
      _jspx_th_clay_image$1card_12.setImageSrc("https://images.unsplash.com/photo-1506020647804-b04ee956dc04");
      _jspx_th_clay_image$1card_12.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_image$1card_12.setSelectable( true );
      _jspx_th_clay_image$1card_12.setSelected( true );
      _jspx_th_clay_image$1card_12.setStickerLabel("JPG");
      _jspx_th_clay_image$1card_12.setStickerStyle("danger");
      _jspx_th_clay_image$1card_12.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_12.setTitle("Beetle");
      int _jspx_eval_clay_image$1card_12 = _jspx_th_clay_image$1card_12.doStartTag();
      if (_jspx_th_clay_image$1card_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_12);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_imageSrc_imageAlt_href_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_12);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_13 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_13.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_13.setParent(null);
      _jspx_th_clay_image$1card_13.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_13.setIcon("camera");
      _jspx_th_clay_image$1card_13.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_image$1card_13.setSelectable( true );
      _jspx_th_clay_image$1card_13.setSelected( false );
      _jspx_th_clay_image$1card_13.setStickerLabel("SVG");
      _jspx_th_clay_image$1card_13.setStickerStyle("warning");
      _jspx_th_clay_image$1card_13.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_13.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_13 = _jspx_th_clay_image$1card_13.doStartTag();
      if (_jspx_th_clay_image$1card_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_13);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_13);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-empty-block\">\n\t\t");
      //  clay:image-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag _jspx_th_clay_image$1card_14 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag) _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ImageCardTag.class);
      _jspx_th_clay_image$1card_14.setPageContext(_jspx_page_context);
      _jspx_th_clay_image$1card_14.setParent(null);
      _jspx_th_clay_image$1card_14.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_image$1card_14.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_image$1card_14.setSelectable( true );
      _jspx_th_clay_image$1card_14.setSelected( true );
      _jspx_th_clay_image$1card_14.setStickerLabel("PNG");
      _jspx_th_clay_image$1card_14.setStickerStyle("info");
      _jspx_th_clay_image$1card_14.setSubtitle("Author Action");
      _jspx_th_clay_image$1card_14.setTitle( SVG_FILE_TITLE );
      int _jspx_eval_clay_image$1card_14 = _jspx_th_clay_image$1card_14.doStartTag();
      if (_jspx_th_clay_image$1card_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_14);
        return;
      }
      _jspx_tagPool_clay_image$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_actionDropdownItems_nobody.reuse(_jspx_th_clay_image$1card_14);
      out.write("\n\t</div>\n</div>\n\n<h4>File Cards</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-4\" id=\"image-card-block\">\n\t\t");
      //  clay:file-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag _jspx_th_clay_file$1card_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag) _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag.class);
      _jspx_th_clay_file$1card_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_file$1card_0.setParent(null);
      _jspx_th_clay_file$1card_0.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_file$1card_0.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_file$1card_0.setStickerLabel("PDF");
      _jspx_th_clay_file$1card_0.setStickerStyle("danger");
      _jspx_th_clay_file$1card_0.setSubtitle("Stevie Ray Vaughn");
      _jspx_th_clay_file$1card_0.setTitle( PDF_FILE_TITLE );
      int _jspx_eval_clay_file$1card_0 = _jspx_th_clay_file$1card_0.doStartTag();
      if (_jspx_th_clay_file$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.reuse(_jspx_th_clay_file$1card_0);
        return;
      }
      _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_labels_actionDropdownItems_nobody.reuse(_jspx_th_clay_file$1card_0);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:file-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag _jspx_th_clay_file$1card_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag) _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_labelStylesMap_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag.class);
      _jspx_th_clay_file$1card_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_file$1card_1.setParent(null);
      _jspx_th_clay_file$1card_1.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_file$1card_1.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_file$1card_1.setLabelStylesMap( cardsDisplayContext.getLabelStylesMap() );
      _jspx_th_clay_file$1card_1.setSelectable( true );
      _jspx_th_clay_file$1card_1.setSelected( true );
      _jspx_th_clay_file$1card_1.setStickerLabel("MP3");
      _jspx_th_clay_file$1card_1.setStickerStyle("warning");
      _jspx_th_clay_file$1card_1.setSubtitle("Jimi Hendrix");
      _jspx_th_clay_file$1card_1.setTitle( MP3_FILE_TITLE );
      int _jspx_eval_clay_file$1card_1 = _jspx_th_clay_file$1card_1.doStartTag();
      if (_jspx_th_clay_file$1card_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_labelStylesMap_actionDropdownItems_nobody.reuse(_jspx_th_clay_file$1card_1);
        return;
      }
      _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_labelStylesMap_actionDropdownItems_nobody.reuse(_jspx_th_clay_file$1card_1);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-4\" id=\"image-card-empty-block\">\n\t\t");
      //  clay:file-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag _jspx_th_clay_file$1card_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag) _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.FileCardTag.class);
      _jspx_th_clay_file$1card_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_file$1card_2.setParent(null);
      _jspx_th_clay_file$1card_2.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_file$1card_2.setIcon("list");
      _jspx_th_clay_file$1card_2.setLabels( cardsDisplayContext.getLabelItems() );
      _jspx_th_clay_file$1card_2.setSelectable( true );
      _jspx_th_clay_file$1card_2.setSelected( true );
      _jspx_th_clay_file$1card_2.setStickerLabel("DOC");
      _jspx_th_clay_file$1card_2.setStickerStyle("info");
      _jspx_th_clay_file$1card_2.setSubtitle("Paco de Lucia");
      _jspx_th_clay_file$1card_2.setTitle( DOC_FILE_TITLE );
      int _jspx_eval_clay_file$1card_2 = _jspx_th_clay_file$1card_2.doStartTag();
      if (_jspx_th_clay_file$1card_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_file$1card_2);
        return;
      }
      _jspx_tagPool_clay_file$1card_title_subtitle_stickerStyle_stickerLabel_selected_selectable_labels_icon_actionDropdownItems_nobody.reuse(_jspx_th_clay_file$1card_2);
      out.write("\n\t</div>\n</div>\n\n<h4>User Cards</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-6\" id=\"image-card-block\">\n\t\t");
      //  clay:user-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.UserCardTag _jspx_th_clay_user$1card_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.UserCardTag) _jspx_tagPool_clay_user$1card_userColor_subtitle_name_initials_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.UserCardTag.class);
      _jspx_th_clay_user$1card_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_user$1card_0.setParent(null);
      _jspx_th_clay_user$1card_0.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_user$1card_0.setInitials("HS");
      _jspx_th_clay_user$1card_0.setName("User Name");
      _jspx_th_clay_user$1card_0.setSubtitle("Latest Action");
      _jspx_th_clay_user$1card_0.setUserColor("danger");
      int _jspx_eval_clay_user$1card_0 = _jspx_th_clay_user$1card_0.doStartTag();
      if (_jspx_th_clay_user$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_user$1card_userColor_subtitle_name_initials_actionDropdownItems_nobody.reuse(_jspx_th_clay_user$1card_0);
        return;
      }
      _jspx_tagPool_clay_user$1card_userColor_subtitle_name_initials_actionDropdownItems_nobody.reuse(_jspx_th_clay_user$1card_0);
      out.write("\n\t</div>\n\n\t<div class=\"col-md-6\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:user-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.UserCardTag _jspx_th_clay_user$1card_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.UserCardTag) _jspx_tagPool_clay_user$1card_subtitle_selected_selectable_name_imageSrc_imageAlt_disabled_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.UserCardTag.class);
      _jspx_th_clay_user$1card_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_user$1card_1.setParent(null);
      _jspx_th_clay_user$1card_1.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_user$1card_1.setDisabled( true );
      _jspx_th_clay_user$1card_1.setImageAlt("thumbnail");
      _jspx_th_clay_user$1card_1.setImageSrc("https://images.unsplash.com/photo-1502290822284-9538ef1f1291");
      _jspx_th_clay_user$1card_1.setName("User name");
      _jspx_th_clay_user$1card_1.setSelectable( true );
      _jspx_th_clay_user$1card_1.setSelected( true );
      _jspx_th_clay_user$1card_1.setSubtitle("Latest Action");
      int _jspx_eval_clay_user$1card_1 = _jspx_th_clay_user$1card_1.doStartTag();
      if (_jspx_th_clay_user$1card_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_user$1card_subtitle_selected_selectable_name_imageSrc_imageAlt_disabled_actionDropdownItems_nobody.reuse(_jspx_th_clay_user$1card_1);
        return;
      }
      _jspx_tagPool_clay_user$1card_subtitle_selected_selectable_name_imageSrc_imageAlt_disabled_actionDropdownItems_nobody.reuse(_jspx_th_clay_user$1card_1);
      out.write("\n\t</div>\n</div>\n\n<h4>Horizontal Cards</h4>\n\n<div class=\"row\">\n\t<div class=\"col-md-6\" id=\"image-card-block\">\n\t\t");
      if (_jspx_meth_clay_horizontal$1card_0(_jspx_page_context))
        return;
      out.write("\n\t</div>\n\n\t<div class=\"col-md-6\" id=\"image-card-icon-block\">\n\t\t");
      //  clay:horizontal-card
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCardTag _jspx_th_clay_horizontal$1card_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCardTag) _jspx_tagPool_clay_horizontal$1card_title_selected_selectable_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCardTag.class);
      _jspx_th_clay_horizontal$1card_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_horizontal$1card_1.setParent(null);
      _jspx_th_clay_horizontal$1card_1.setActionDropdownItems( cardsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_horizontal$1card_1.setSelectable( true );
      _jspx_th_clay_horizontal$1card_1.setSelected( true );
      _jspx_th_clay_horizontal$1card_1.setTitle("ReallySuperInsanelyJustIncrediblyLongAndTotallyNotPossibleWordButWeAreReallyTryingToCoverAllOurBasesHereJustInCaseSomeoneIsNutsAsPerUsual");
      int _jspx_eval_clay_horizontal$1card_1 = _jspx_th_clay_horizontal$1card_1.doStartTag();
      if (_jspx_th_clay_horizontal$1card_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_horizontal$1card_title_selected_selectable_actionDropdownItems_nobody.reuse(_jspx_th_clay_horizontal$1card_1);
        return;
      }
      _jspx_tagPool_clay_horizontal$1card_title_selected_selectable_actionDropdownItems_nobody.reuse(_jspx_th_clay_horizontal$1card_1);
      out.write("\n\t</div>\n</div>");
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

  private boolean _jspx_meth_clay_horizontal$1card_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  clay:horizontal-card
    com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCardTag _jspx_th_clay_horizontal$1card_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCardTag) _jspx_tagPool_clay_horizontal$1card_title_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCardTag.class);
    _jspx_th_clay_horizontal$1card_0.setPageContext(_jspx_page_context);
    _jspx_th_clay_horizontal$1card_0.setParent(null);
    _jspx_th_clay_horizontal$1card_0.setTitle("ReallySuperInsanelyJustIncrediblyLongAndTotallyNotPossibleWordButWeAreReallyTryingToCoverAllOurBasesHereJustInCaseSomeoneIsNutsAsPerUsual");
    int _jspx_eval_clay_horizontal$1card_0 = _jspx_th_clay_horizontal$1card_0.doStartTag();
    if (_jspx_th_clay_horizontal$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_clay_horizontal$1card_title_nobody.reuse(_jspx_th_clay_horizontal$1card_0);
      return true;
    }
    _jspx_tagPool_clay_horizontal$1card_title_nobody.reuse(_jspx_th_clay_horizontal$1card_0);
    return false;
  }
}
