package org.apache.jsp.repository_005fentry_005fbrowser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.item.selector.taglib.internal.dao.search.RepositoryEntryResultRowSplitter;
import com.liferay.item.selector.taglib.internal.security.permission.resource.DLFolderPermission;
import com.liferay.item.selector.taglib.internal.servlet.ServletContextUtil;
import com.liferay.item.selector.taglib.internal.util.ItemSelectorRepositoryEntryBrowserUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletURL;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.taglib.internal.display.context.ItemSelectorRepositoryEntryManagementToolbarDisplayContext;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;

public final class page_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/repository_entry_browser/init.jsp");
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_app$1view$1entry_url_title_showCheckbox_modifiedDate_markupView_folder_displayStyle_description_createDate_author_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card_title_imageUrl_data_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_horizontal$1card$1col;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_icon_data_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_buffer_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_var_total_searchContainer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showInfoButton_selectable_searchFormName_searchFormMethod_searchActionURL_itemsTotal_filterDropdownItems_disabled_clearResultsURL_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_resultRow;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_app$1view$1entry_version_title_status_showCheckbox_modifiedDate_markupView_groupId_displayStyle_description_createDate_author_assetTagClassPK_assetTagClassName_assetCategoryClassPK_assetCategoryClassName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_html$1top;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_drop$1here$1info_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_app$1view$1entry_url_title_showCheckbox_modifiedDate_markupView_folder_displayStyle_description_createDate_author_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card_title_imageUrl_data_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1col = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_icon_data_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_buffer_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_var_total_searchContainer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showInfoButton_selectable_searchFormName_searchFormMethod_searchActionURL_itemsTotal_filterDropdownItems_disabled_clearResultsURL_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_resultRow = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_app$1view$1entry_version_title_status_showCheckbox_modifiedDate_markupView_groupId_displayStyle_description_createDate_author_assetTagClassPK_assetTagClassName_assetCategoryClassPK_assetCategoryClassName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_html$1top = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_drop$1here$1info_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_liferay$1ui_app$1view$1entry_url_title_showCheckbox_modifiedDate_markupView_folder_displayStyle_description_createDate_author_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card_title_imageUrl_data_cssClass.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.release();
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.release();
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_icon_data_cssClass.release();
    _jspx_tagPool_liferay$1util_buffer_var.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_search$1container_var_total_searchContainer.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showInfoButton_selectable_searchFormName_searchFormMethod_searchActionURL_itemsTotal_filterDropdownItems_disabled_clearResultsURL_nobody.release();
    _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_resultRow.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1ui_app$1view$1entry_version_title_status_showCheckbox_modifiedDate_markupView_groupId_displayStyle_description_createDate_author_assetTagClassPK_assetTagClassName_assetCategoryClassPK_assetCategoryClassName_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_liferay$1util_html$1top.release();
    _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.release();
    _jspx_tagPool_liferay$1ui_drop$1here$1info_message_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
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
      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      //  liferay-frontend:defineObjects
      com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1frontend_defineObjects_0 = (com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1frontend_defineObjects_nobody.get(com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1frontend_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1frontend_defineObjects_0 = _jspx_th_liferay$1frontend_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1frontend_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
      java.lang.String currentURL = null;
      javax.portlet.PortletURL currentURLObj = null;
      java.util.ResourceBundle resourceBundle = null;
      javax.portlet.WindowState windowState = null;
      currentURL = (java.lang.String) _jspx_page_context.findAttribute("currentURL");
      currentURLObj = (javax.portlet.PortletURL) _jspx_page_context.findAttribute("currentURLObj");
      resourceBundle = (java.util.ResourceBundle) _jspx_page_context.findAttribute("resourceBundle");
      windowState = (javax.portlet.WindowState) _jspx_page_context.findAttribute("windowState");
      out.write('\n');
      out.write('\n');
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
      //  portlet:defineObjects
      com.liferay.taglib.portlet.DefineObjectsTag _jspx_th_portlet_defineObjects_0 = (com.liferay.taglib.portlet.DefineObjectsTag) _jspx_tagPool_portlet_defineObjects_nobody.get(com.liferay.taglib.portlet.DefineObjectsTag.class);
      _jspx_th_portlet_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_defineObjects_0.setParent(null);
      int _jspx_eval_portlet_defineObjects_0 = _jspx_th_portlet_defineObjects_0.doStartTag();
      if (_jspx_th_portlet_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
        return;
      }
      _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
      javax.portlet.ActionRequest actionRequest = null;
      javax.portlet.ActionResponse actionResponse = null;
      javax.portlet.EventRequest eventRequest = null;
      javax.portlet.EventResponse eventResponse = null;
      com.liferay.portal.kernel.portlet.LiferayPortletRequest liferayPortletRequest = null;
      com.liferay.portal.kernel.portlet.LiferayPortletResponse liferayPortletResponse = null;
      javax.portlet.PortletConfig portletConfig = null;
      java.lang.String portletName = null;
      javax.portlet.PortletPreferences portletPreferences = null;
      java.util.Map portletPreferencesValues = null;
      javax.portlet.PortletSession portletSession = null;
      java.util.Map portletSessionScope = null;
      javax.portlet.RenderRequest renderRequest = null;
      javax.portlet.RenderResponse renderResponse = null;
      javax.portlet.ResourceRequest resourceRequest = null;
      javax.portlet.ResourceResponse resourceResponse = null;
      actionRequest = (javax.portlet.ActionRequest) _jspx_page_context.findAttribute("actionRequest");
      actionResponse = (javax.portlet.ActionResponse) _jspx_page_context.findAttribute("actionResponse");
      eventRequest = (javax.portlet.EventRequest) _jspx_page_context.findAttribute("eventRequest");
      eventResponse = (javax.portlet.EventResponse) _jspx_page_context.findAttribute("eventResponse");
      liferayPortletRequest = (com.liferay.portal.kernel.portlet.LiferayPortletRequest) _jspx_page_context.findAttribute("liferayPortletRequest");
      liferayPortletResponse = (com.liferay.portal.kernel.portlet.LiferayPortletResponse) _jspx_page_context.findAttribute("liferayPortletResponse");
      portletConfig = (javax.portlet.PortletConfig) _jspx_page_context.findAttribute("portletConfig");
      portletName = (java.lang.String) _jspx_page_context.findAttribute("portletName");
      portletPreferences = (javax.portlet.PortletPreferences) _jspx_page_context.findAttribute("portletPreferences");
      portletPreferencesValues = (java.util.Map) _jspx_page_context.findAttribute("portletPreferencesValues");
      portletSession = (javax.portlet.PortletSession) _jspx_page_context.findAttribute("portletSession");
      portletSessionScope = (java.util.Map) _jspx_page_context.findAttribute("portletSessionScope");
      renderRequest = (javax.portlet.RenderRequest) _jspx_page_context.findAttribute("renderRequest");
      renderResponse = (javax.portlet.RenderResponse) _jspx_page_context.findAttribute("renderResponse");
      resourceRequest = (javax.portlet.ResourceRequest) _jspx_page_context.findAttribute("resourceRequest");
      resourceResponse = (javax.portlet.ResourceResponse) _jspx_page_context.findAttribute("resourceResponse");
      out.write("\n\n\n\n");
      out.write('\n');
      out.write('\n');

String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_repository_entry_browse_page") + StringPool.UNDERLINE;

String displayStyle = GetterUtil.getString(request.getAttribute("liferay-item-selector:repository-entry-browser:displayStyle"));
DLMimeTypeDisplayContext dlMimeTypeDisplayContext = (DLMimeTypeDisplayContext)request.getAttribute("liferay-item-selector:repository-entry-browser:dlMimeTypeDisplayContext");
String emptyResultsMessage = GetterUtil.getString(request.getAttribute("liferay-item-selector:repository-entry-browser:emptyResultsMessage"));
ItemSelectorReturnType existingFileEntryReturnType = (ItemSelectorReturnType)request.getAttribute("liferay-item-selector:repository-entry-browser:existingFileEntryReturnType");
List<String> extensions = (List)request.getAttribute("liferay-item-selector:repository-entry-browser:extensions");
String itemSelectedEventName = GetterUtil.getString(request.getAttribute("liferay-item-selector:repository-entry-browser:itemSelectedEventName"));
ItemSelectorReturnTypeResolver itemSelectorReturnTypeResolver = (ItemSelectorReturnTypeResolver)request.getAttribute("liferay-item-selector:repository-entry-browser:itemSelectorReturnTypeResolver");
long maxFileSize = GetterUtil.getLong(request.getAttribute("liferay-item-selector:repository-entry-browser:maxFileSize"));
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-item-selector:repository-entry-browser:portletURL");
List repositoryEntries = (List)request.getAttribute("liferay-item-selector:repository-entry-browser:repositoryEntries");
int repositoryEntriesCount = GetterUtil.getInteger(request.getAttribute("liferay-item-selector:repository-entry-browser:repositoryEntriesCount"));
boolean showBreadcrumb = GetterUtil.getBoolean(request.getAttribute("liferay-item-selector:repository-entry-browser:showBreadcrumb"));
boolean showDragAndDropZone = GetterUtil.getBoolean(request.getAttribute("liferay-item-selector:repository-entry-browser:showDragAndDropZone"));
boolean showSearch = GetterUtil.getBoolean(request.getAttribute("liferay-item-selector:repository-entry-browser:showSearch"));
String tabName = GetterUtil.getString(request.getAttribute("liferay-item-selector:repository-entry-browser:tabName"));
PortletURL uploadURL = (PortletURL)request.getAttribute("liferay-item-selector:repository-entry-browser:uploadURL");

SearchContainer searchContainer = new SearchContainer(renderRequest, PortletURLUtil.clone(portletURL, liferayPortletResponse), null, emptyResultsMessage);

String keywords = ParamUtil.getString(request, "keywords");

boolean showSearchInfo = false;

if (Validator.isNotNull(keywords)) {
	showSearchInfo = true;
}

      out.write('\n');
      out.write('\n');
      //  liferay-util:html-top
      com.liferay.taglib.util.HtmlTopTag _jspx_th_liferay$1util_html$1top_0 = (com.liferay.taglib.util.HtmlTopTag) _jspx_tagPool_liferay$1util_html$1top.get(com.liferay.taglib.util.HtmlTopTag.class);
      _jspx_th_liferay$1util_html$1top_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_html$1top_0.setParent(null);
      int _jspx_eval_liferay$1util_html$1top_0 = _jspx_th_liferay$1util_html$1top_0.doStartTag();
      if (_jspx_eval_liferay$1util_html$1top_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1util_html$1top_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1util_html$1top_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1util_html$1top_0.doInitBody();
        }
        do {
          out.write("\n\t<link href=\"");
          out.print( ServletContextUtil.getContextPath() + "/repository_entry_browser/css/main.css" );
          out.write("\" rel=\"stylesheet\" type=\"text/css\" />\n");
          int evalDoAfterBody = _jspx_th_liferay$1util_html$1top_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1util_html$1top_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1util_html$1top_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1util_html$1top.reuse(_jspx_th_liferay$1util_html$1top_0);
        return;
      }
      _jspx_tagPool_liferay$1util_html$1top.reuse(_jspx_th_liferay$1util_html$1top_0);
      out.write('\n');
      out.write('\n');

ItemSelectorRepositoryEntryManagementToolbarDisplayContext itemSelectorRepositoryEntryManagementToolbarDisplayContext = new ItemSelectorRepositoryEntryManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request);

      out.write('\n');
      out.write('\n');
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showInfoButton_selectable_searchFormName_searchFormMethod_searchActionURL_itemsTotal_filterDropdownItems_disabled_clearResultsURL_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_0.setParent(null);
      _jspx_th_clay_management$1toolbar_0.setClearResultsURL( String.valueOf(itemSelectorRepositoryEntryManagementToolbarDisplayContext.getSearchURL()) );
      _jspx_th_clay_management$1toolbar_0.setDisabled( itemSelectorRepositoryEntryManagementToolbarDisplayContext.isDisabled() );
      _jspx_th_clay_management$1toolbar_0.setFilterDropdownItems( itemSelectorRepositoryEntryManagementToolbarDisplayContext.getFilterDropdownItems() );
      _jspx_th_clay_management$1toolbar_0.setItemsTotal( repositoryEntriesCount );
      _jspx_th_clay_management$1toolbar_0.setSearchActionURL( String.valueOf(itemSelectorRepositoryEntryManagementToolbarDisplayContext.getSearchURL()) );
      _jspx_th_clay_management$1toolbar_0.setSearchFormMethod("POST");
      _jspx_th_clay_management$1toolbar_0.setSearchFormName("searchFm");
      _jspx_th_clay_management$1toolbar_0.setSelectable( false );
      _jspx_th_clay_management$1toolbar_0.setShowInfoButton( false );
      _jspx_th_clay_management$1toolbar_0.setShowSearch( showSearch );
      _jspx_th_clay_management$1toolbar_0.setSortingOrder( itemSelectorRepositoryEntryManagementToolbarDisplayContext.getOrderByType() );
      _jspx_th_clay_management$1toolbar_0.setSortingURL( String.valueOf(itemSelectorRepositoryEntryManagementToolbarDisplayContext.getSortingURL()) );
      _jspx_th_clay_management$1toolbar_0.setViewTypeItems( itemSelectorRepositoryEntryManagementToolbarDisplayContext.getViewTypes() );
      int _jspx_eval_clay_management$1toolbar_0 = _jspx_th_clay_management$1toolbar_0.doStartTag();
      if (_jspx_th_clay_management$1toolbar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showInfoButton_selectable_searchFormName_searchFormMethod_searchActionURL_itemsTotal_filterDropdownItems_disabled_clearResultsURL_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingURL_sortingOrder_showSearch_showInfoButton_selectable_searchFormName_searchFormMethod_searchActionURL_itemsTotal_filterDropdownItems_disabled_clearResultsURL_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
      out.write("\n\n<div class=\"container-fluid container-fluid-max-xl lfr-item-viewer\" id=\"");
      out.print( randomNamespace );
      out.write("ItemSelectorContainer\">\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( showSearchInfo );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-util:include
        com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
        _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_liferay$1util_include_0.setPage("/repository_entry_browser/search_info.jsp");
        _jspx_th_liferay$1util_include_0.setServletContext( application );
        int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
        if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
          return;
        }
        _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write("\n\n\t");

	long folderId = ParamUtil.getLong(request, "folderId");

	if (showBreadcrumb && !showSearchInfo) {
		ItemSelectorRepositoryEntryBrowserUtil.addPortletBreadcrumbEntries(folderId, displayStyle, request, liferayPortletResponse, PortletURLUtil.clone(portletURL, liferayPortletResponse));
	
      out.write("\n\n\t\t");
      //  liferay-ui:breadcrumb
      com.liferay.taglib.ui.BreadcrumbTag _jspx_th_liferay$1ui_breadcrumb_0 = (com.liferay.taglib.ui.BreadcrumbTag) _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.get(com.liferay.taglib.ui.BreadcrumbTag.class);
      _jspx_th_liferay$1ui_breadcrumb_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_breadcrumb_0.setParent(null);
      _jspx_th_liferay$1ui_breadcrumb_0.setShowCurrentGroup( false );
      _jspx_th_liferay$1ui_breadcrumb_0.setShowGuestGroup( false );
      _jspx_th_liferay$1ui_breadcrumb_0.setShowLayout( false );
      _jspx_th_liferay$1ui_breadcrumb_0.setShowParentGroups( false );
      int _jspx_eval_liferay$1ui_breadcrumb_0 = _jspx_th_liferay$1ui_breadcrumb_0.doStartTag();
      if (_jspx_th_liferay$1ui_breadcrumb_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_breadcrumb_showParentGroups_showLayout_showGuestGroup_showCurrentGroup_nobody.reuse(_jspx_th_liferay$1ui_breadcrumb_0);
      out.write("\n\n\t");

	}
	
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest( showDragAndDropZone && !showSearchInfo && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) );
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-util:buffer
        com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_0 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
        _jspx_th_liferay$1util_buffer_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1util_buffer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        _jspx_th_liferay$1util_buffer_0.setVar("selectFileHTML");
        int _jspx_eval_liferay$1util_buffer_0 = _jspx_th_liferay$1util_buffer_0.doStartTag();
        if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1util_buffer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1util_buffer_0.doInitBody();
          }
          do {
            out.write("\n\t\t\t<label class=\"btn btn-default\" for=\"");
            out.print( randomNamespace );
            out.write("InputFile\">");
            if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1util_buffer_0, _jspx_page_context))
              return;
            out.write("</label>\n\n\t\t\t<input accept=\"");
            out.print( ListUtil.isEmpty(extensions) ? "*" : StringUtil.merge(extensions) );
            out.write("\" class=\"hide\" id=\"");
            out.print( randomNamespace );
            out.write("InputFile\" type=\"file\" />\n\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1util_buffer_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1util_buffer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
          return;
        }
        _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
        java.lang.String selectFileHTML = null;
        selectFileHTML = (java.lang.String) _jspx_page_context.findAttribute("selectFileHTML");
        out.write("\n\n\t\t<div class=\"drop-enabled drop-zone no-border\">\n\t\t\t");
        //  c:choose
        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
        _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
        _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
        if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t");
          //  c:when
          com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
          _jspx_th_c_when_0.setPageContext(_jspx_page_context);
          _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
          _jspx_th_c_when_0.setTest( BrowserSnifferUtil.isMobile(request) );
          int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
          if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            out.print( selectFileHTML );
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            return;
          }
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          out.write("\n\t\t\t\t");
          //  c:otherwise
          com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
          _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
          _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
          int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
          if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t<strong>");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
            _jspx_th_liferay$1ui_message_1.setArguments( selectFileHTML );
            _jspx_th_liferay$1ui_message_1.setKey("drag-and-drop-to-upload-or-x");
            int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
            if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
            out.write("</strong>\n\t\t\t\t");
          }
          if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
            return;
          }
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          return;
        }
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        out.write("\n\t\t</div>\n\t");
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write("\n\n\t");
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_2.setPageContext(_jspx_page_context);
      _jspx_th_c_if_2.setParent(null);
      _jspx_th_c_if_2.setTest( (existingFileEntryReturnType != null) || (itemSelectorReturnTypeResolver != null) );
      int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
      if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  liferay-ui:search-container
        com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_var_total_searchContainer.get(com.liferay.taglib.ui.SearchContainerTag.class);
        _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
        _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( searchContainer );
        _jspx_th_liferay$1ui_search$1container_0.setTotal( repositoryEntriesCount );
        _jspx_th_liferay$1ui_search$1container_0.setVar("listSearchContainer");
        int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer total = null;
          com.liferay.portal.kernel.dao.search.SearchContainer listSearchContainer = null;
          total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
          listSearchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("listSearchContainer");
          out.write("\n\t\t\t");
          //  liferay-ui:search-container-results
          java.util.List results = null;
          java.lang.Integer deprecatedTotal = null;
          com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
          _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1results_0.setResults( repositoryEntries );
          int _jspx_eval_liferay$1ui_search$1container$1results_0 = _jspx_th_liferay$1ui_search$1container$1results_0.doStartTag();
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          if (_jspx_th_liferay$1ui_search$1container$1results_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_0);
            return;
          }
          results = (java.util.List) _jspx_page_context.findAttribute("results");
          deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
          _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_0);
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-container-row
          com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
          _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.repository.model.RepositoryEntry");
          _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("repositoryEntry");
          int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer index = null;
            com.liferay.portal.kernel.repository.model.RepositoryEntry repositoryEntry = null;
            com.liferay.portal.kernel.dao.search.ResultRow row = null;
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
            }
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            repositoryEntry = (com.liferay.portal.kernel.repository.model.RepositoryEntry) _jspx_page_context.findAttribute("repositoryEntry");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            do {
              out.write("\n\t\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
              int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
              if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                _jspx_th_c_when_1.setTest( displayStyle.equals("list") );
                int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t");

						FileEntry fileEntry = null;
						FileShortcut fileShortcut = null;
						Folder folder = null;

						if (repositoryEntry instanceof FileEntry) {
							fileEntry = (FileEntry)repositoryEntry;
						}
						else if (repositoryEntry instanceof FileShortcut) {
							fileShortcut = (FileShortcut)repositoryEntry;

							fileEntry = DLAppLocalServiceUtil.getFileEntry(fileShortcut.getToFileEntryId());
						}
						else {
							folder = (Folder)repositoryEntry;
						}

						if (fileEntry != null) {
							FileVersion latestFileVersion = fileEntry.getLatestFileVersion();

							String title = DLUtil.getTitleWithExtension(fileEntry);

							JSONObject itemMedatadaJSONObject = ItemSelectorRepositoryEntryBrowserUtil.getItemMetadataJSONObject(fileEntry, locale);

							String thumbnailSrc = DLUtil.getThumbnailSrc(fileEntry, themeDisplay);
						
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("title");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t\t<a class=\"item-preview\" data-metadata=\"");
                      out.print( HtmlUtil.escapeAttribute(itemMedatadaJSONObject.toString()) );
                      out.write("\" data-returnType=\"");
                      out.print( HtmlUtil.escapeAttribute(ItemSelectorRepositoryEntryBrowserUtil.getItemSelectorReturnTypeClassName(itemSelectorReturnTypeResolver, existingFileEntryReturnType)) );
                      out.write("\" data-url=\"");
                      out.print( HtmlUtil.escapeAttribute(DLUtil.getPreviewURL(fileEntry, latestFileVersion, themeDisplay, StringPool.BLANK)) );
                      out.write("\" data-value=\"");
                      out.print( HtmlUtil.escapeAttribute(ItemSelectorRepositoryEntryBrowserUtil.getValue(itemSelectorReturnTypeResolver, existingFileEntryReturnType, fileEntry, themeDisplay)) );
                      out.write("\" href=\"");
                      out.print( Validator.isNotNull(thumbnailSrc) ? HtmlUtil.escapeHREF(DLUtil.getImagePreviewURL(fileEntry, themeDisplay)) : themeDisplay.getPathThemeImages() + "/file_system/large/default.png" );
                      out.write("\" title=\"");
                      out.print( HtmlUtil.escapeAttribute(title) );
                      out.write("\">\n\n\t\t\t\t\t\t\t\t\t");

									String iconCssClass = DLUtil.getFileIconCssClass(fileEntry.getExtension());
									
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                      _jspx_th_c_if_3.setTest( Validator.isNotNull(iconCssClass) );
                      int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                      if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t<i class=\"");
                        out.print( iconCssClass );
                        out.write("\"></i>\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t<span class=\"taglib-text\">\n\t\t\t\t\t\t\t\t\t\t");
                      out.print( HtmlUtil.escape(title) );
                      out.write("\n\t\t\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("size");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_1.setValue( TextFormatter.formatStorageSize(fileEntry.getSize(), locale) );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-status
                  com.liferay.taglib.ui.SearchContainerColumnStatusTag _jspx_th_liferay$1ui_search$1container$1column$1status_0 = (com.liferay.taglib.ui.SearchContainerColumnStatusTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnStatusTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setName("status");
                  _jspx_th_liferay$1ui_search$1container$1column$1status_0.setStatus( latestFileVersion.getStatus() );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1status_0 = _jspx_th_liferay$1ui_search$1container$1column$1status_0.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1status_status_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1status_0);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("modified-date");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_2.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                      _jspx_th_liferay$1ui_message_2.setArguments( new String[] {LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - fileEntry.getModifiedDate().getTime(), true), HtmlUtil.escape(fileEntry.getUserName())} );
                      _jspx_th_liferay$1ui_message_2.setKey("x-ago-by-x");
                      _jspx_th_liferay$1ui_message_2.setTranslateArguments( false );
                      int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                      if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                      out.write("\n\t\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                  out.write("\n\n\t\t\t\t\t\t");

						}

						if (folder != null) {
							PortletURL viewFolderURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

							viewFolderURL.setParameter("folderId", String.valueOf(folder.getFolderId()));
						
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("title");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_3.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t\t<a href=\"");
                      out.print( HtmlUtil.escapeHREF(viewFolderURL.toString()) );
                      out.write("\" title=\"");
                      out.print( HtmlUtil.escapeAttribute(folder.getName()) );
                      out.write("\">\n\t\t\t\t\t\t\t\t\t<i class=\"icon-folder-open\"></i>\n\n\t\t\t\t\t\t\t\t\t<span class=\"taglib-text\">\n\t\t\t\t\t\t\t\t\t\t");
                      out.print( HtmlUtil.escape(folder.getName()) );
                      out.write("\n\t\t\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t\t\t</a>\n\t\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  out.write("\n\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1text_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                    return;
                  out.write("\n\n\t\t\t\t\t\t\t");
                  if (_jspx_meth_liferay$1ui_search$1container$1column$1text_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                    return;
                  out.write("\n\n\t\t\t\t\t\t\t");
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setName("modified-date");
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_liferay$1ui_search$1container$1column$1text_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_6.doInitBody();
                    }
                    do {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_6);
                      _jspx_th_liferay$1ui_message_3.setArguments( new String[] {LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - folder.getModifiedDate().getTime(), true), HtmlUtil.escape(folder.getUserName())} );
                      _jspx_th_liferay$1ui_message_3.setKey("x-ago-by-x");
                      _jspx_th_liferay$1ui_message_3.setTranslateArguments( false );
                      int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
                      if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                      out.write("\n\t\t\t\t\t\t\t");
                      int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_liferay$1ui_search$1container$1column$1text_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                      out = _jspx_page_context.popBody();
                  }
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                  out.write("\n\n\t\t\t\t\t\t");

						}
						
                  out.write("\n\n\t\t\t\t\t");
                }
                if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                out.write("\n\t\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t\t");

						FileEntry fileEntry = null;
						Folder folder = null;

						if (repositoryEntry instanceof FileEntry) {
							fileEntry = (FileEntry)repositoryEntry;
						}
						else if (repositoryEntry instanceof FileShortcut) {
							FileShortcut fileShortcut = (FileShortcut)repositoryEntry;

							fileEntry = DLAppLocalServiceUtil.getFileEntry(fileShortcut.getToFileEntryId());
						}
						else {
							folder = (Folder)repositoryEntry;
						}
						
                  out.write("\n\n\t\t\t\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                  int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                  if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    _jspx_th_c_when_2.setTest( displayStyle.equals("icon") );
                    int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                    if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t\t\t\t\t\t");

								row.setCssClass("entry-card lfr-asset-folder");

								if (folder != null) {
									PortletURL viewFolderURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

									viewFolderURL.setParameter("folderId", String.valueOf(folder.getFolderId()));
								
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_7.setColspan( 3 );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_7.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          //  liferay-frontend:horizontal-card
                          com.liferay.frontend.taglib.servlet.taglib.HorizontalCardTag _jspx_th_liferay$1frontend_horizontal$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.HorizontalCardTag) _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_resultRow.get(com.liferay.frontend.taglib.servlet.taglib.HorizontalCardTag.class);
                          _jspx_th_liferay$1frontend_horizontal$1card_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_horizontal$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_7);
                          _jspx_th_liferay$1frontend_horizontal$1card_0.setResultRow( row );
                          _jspx_th_liferay$1frontend_horizontal$1card_0.setText( folder.getName() );
                          _jspx_th_liferay$1frontend_horizontal$1card_0.setUrl( viewFolderURL.toString() );
                          int _jspx_eval_liferay$1frontend_horizontal$1card_0 = _jspx_th_liferay$1frontend_horizontal$1card_0.doStartTag();
                          if (_jspx_eval_liferay$1frontend_horizontal$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            if (_jspx_meth_liferay$1frontend_horizontal$1card$1col_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_horizontal$1card_0, _jspx_page_context))
                              return;
                            out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_liferay$1frontend_horizontal$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_resultRow.reuse(_jspx_th_liferay$1frontend_horizontal$1card_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_horizontal$1card_url_text_resultRow.reuse(_jspx_th_liferay$1frontend_horizontal$1card_0);
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                      out.write("\n\n\t\t\t\t\t\t\t\t");

								}

								if (fileEntry != null) {
									FileVersion latestFileVersion = fileEntry.getLatestFileVersion();

									String title = DLUtil.getTitleWithExtension(fileEntry);

									JSONObject itemMedatadaJSONObject = ItemSelectorRepositoryEntryBrowserUtil.getItemMetadataJSONObject(fileEntry, locale);

									Map<String, Object> data = new HashMap<String, Object>();

									String thumbnailSrc = DLUtil.getThumbnailSrc(fileEntry, themeDisplay);

									if (Validator.isNotNull(thumbnailSrc)) {
										data.put("href", DLUtil.getImagePreviewURL(fileEntry, themeDisplay));
									}
									else {
										data.put("href", themeDisplay.getPathThemeImages() + "/file_system/large/default.png");
									}

									data.put("metadata", itemMedatadaJSONObject.toString());
									data.put("returnType", ItemSelectorRepositoryEntryBrowserUtil.getItemSelectorReturnTypeClassName(itemSelectorReturnTypeResolver, existingFileEntryReturnType));
									data.put("title", title);
									data.put("url", DLUtil.getPreviewURL(fileEntry, latestFileVersion, themeDisplay, StringPool.BLANK));
									data.put("value", ItemSelectorRepositoryEntryBrowserUtil.getValue(itemSelectorReturnTypeResolver, existingFileEntryReturnType, fileEntry, themeDisplay));

									String stickerCssClass = "file-icon-color-0";

									String fileExtensionSticker = StringUtil.shorten(StringUtil.upperCase(fileEntry.getExtension()), 3, StringPool.BLANK);

									if (Validator.isNotNull(dlMimeTypeDisplayContext)) {
										stickerCssClass = dlMimeTypeDisplayContext.getCssClassFileMimeType(fileEntry.getMimeType());
									}
								
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_8 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_8 = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_8.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          //  c:choose
                          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                          _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                          _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                          int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                          if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            //  c:when
                            com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                            _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                            _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                            _jspx_th_c_when_3.setTest( Validator.isNull(thumbnailSrc) );
                            int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                            if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  liferay-frontend:icon-vertical-card
                              com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag _jspx_th_liferay$1frontend_icon$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag) _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_icon_data_cssClass.get(com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag.class);
                              _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                              _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setCssClass("item-preview");
                              _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setData( data );
                              _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setIcon("documents-and-media");
                              _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setTitle( title );
                              int _jspx_eval_liferay$1frontend_icon$1vertical$1card_0 = _jspx_th_liferay$1frontend_icon$1vertical$1card_0.doStartTag();
                              if (_jspx_eval_liferay$1frontend_icon$1vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  liferay-frontend:vertical-card-sticker-bottom
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"sticker sticker-secondary sticker-bottom-left ");
                              out.print( stickerCssClass );
                              out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              out.print( fileExtensionSticker );
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_0);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_liferay$1frontend_icon$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_icon_data_cssClass.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_title_icon_data_cssClass.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                              return;
                            }
                            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            //  c:otherwise
                            com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                            _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                            _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                            int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                            if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  liferay-frontend:vertical-card
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag _jspx_th_liferay$1frontend_vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag) _jspx_tagPool_liferay$1frontend_vertical$1card_title_imageUrl_data_cssClass.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card_0.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                              _jspx_th_liferay$1frontend_vertical$1card_0.setCssClass("item-preview");
                              _jspx_th_liferay$1frontend_vertical$1card_0.setData( data );
                              _jspx_th_liferay$1frontend_vertical$1card_0.setImageUrl( thumbnailSrc );
                              _jspx_th_liferay$1frontend_vertical$1card_0.setTitle( title );
                              int _jspx_eval_liferay$1frontend_vertical$1card_0 = _jspx_th_liferay$1frontend_vertical$1card_0.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              //  liferay-frontend:vertical-card-sticker-bottom
                              com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardStickerBottomTag.class);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card_0);
                              int _jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doStartTag();
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doInitBody();
                              }
                              do {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"sticker sticker-bottom ");
                              out.print( stickerCssClass );
                              out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              out.print( fileExtensionSticker );
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              if (_jspx_eval_liferay$1frontend_vertical$1card$1sticker$1bottom_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card$1sticker$1bottom.reuse(_jspx_th_liferay$1frontend_vertical$1card$1sticker$1bottom_1);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
                              }
                              if (_jspx_th_liferay$1frontend_vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1frontend_vertical$1card_title_imageUrl_data_cssClass.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                              return;
                              }
                              _jspx_tagPool_liferay$1frontend_vertical$1card_title_imageUrl_data_cssClass.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                            }
                            if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                              return;
                            }
                            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                            out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          }
                          if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                            return;
                          }
                          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                      out.write("\n\n\t\t\t\t\t\t\t\t");

								}
								
                      out.write("\n\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                    out.write("\n\t\t\t\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                    int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
                    if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t\t\t\t\t\t\t");

								if (folder != null) {
									PortletURL viewFolderURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

									viewFolderURL.setParameter("folderId", String.valueOf(folder.getFolderId()));

									String folderImage = "folder_empty_document";

									if (PropsValues.DL_FOLDER_ICON_CHECK_COUNT && (DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(folder.getRepositoryId(), folder.getFolderId(), WorkflowConstants.STATUS_APPROVED, true) > 0)) {
										folderImage = "folder_full_document";
									}
								
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-image
                      com.liferay.taglib.ui.SearchContainerColumnImageTag _jspx_th_liferay$1ui_search$1container$1column$1image_0 = (com.liferay.taglib.ui.SearchContainerColumnImageTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.get(com.liferay.taglib.ui.SearchContainerColumnImageTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1image_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1image_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                      _jspx_th_liferay$1ui_search$1container$1column$1image_0.setSrc( themeDisplay.getPathThemeImages() + "/file_system/large/" + folderImage + ".png" );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1image_0 = _jspx_th_liferay$1ui_search$1container$1column$1image_0.doStartTag();
                      if (_jspx_th_liferay$1ui_search$1container$1column$1image_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1image_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1image_0);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_9 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_9.setColspan( 3 );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_9 = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_9.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                          //  liferay-ui:app-view-entry
                          com.liferay.taglib.ui.AppViewEntryTag _jspx_th_liferay$1ui_app$1view$1entry_0 = (com.liferay.taglib.ui.AppViewEntryTag) _jspx_tagPool_liferay$1ui_app$1view$1entry_url_title_showCheckbox_modifiedDate_markupView_folder_displayStyle_description_createDate_author_nobody.get(com.liferay.taglib.ui.AppViewEntryTag.class);
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_9);
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setAuthor( folder.getUserName() );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setCreateDate( folder.getCreateDate() );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setDescription( folder.getDescription() );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setDisplayStyle("descriptive");
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setFolder( true );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setMarkupView("lexicon");
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setModifiedDate( folder.getModifiedDate() );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setShowCheckbox( false );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setTitle( folder.getName() );
                          _jspx_th_liferay$1ui_app$1view$1entry_0.setUrl( viewFolderURL.toString() );
                          int _jspx_eval_liferay$1ui_app$1view$1entry_0 = _jspx_th_liferay$1ui_app$1view$1entry_0.doStartTag();
                          if (_jspx_th_liferay$1ui_app$1view$1entry_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_app$1view$1entry_url_title_showCheckbox_modifiedDate_markupView_folder_displayStyle_description_createDate_author_nobody.reuse(_jspx_th_liferay$1ui_app$1view$1entry_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_app$1view$1entry_url_title_showCheckbox_modifiedDate_markupView_folder_displayStyle_description_createDate_author_nobody.reuse(_jspx_th_liferay$1ui_app$1view$1entry_0);
                          out.write("\n\t\t\t\t\t\t\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                      out.write("\n\n\t\t\t\t\t\t\t\t");

								}

								if (fileEntry != null) {
									FileVersion latestFileVersion = fileEntry.getLatestFileVersion();

									String title = DLUtil.getTitleWithExtension(fileEntry);

									JSONObject itemMedatadaJSONObject = ItemSelectorRepositoryEntryBrowserUtil.getItemMetadataJSONObject(fileEntry, locale);

									String thumbnailSrc = DLUtil.getThumbnailSrc(fileEntry, themeDisplay);
								
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  c:choose
                      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_4 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                      _jspx_th_c_choose_4.setPageContext(_jspx_page_context);
                      _jspx_th_c_choose_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                      int _jspx_eval_c_choose_4 = _jspx_th_c_choose_4.doStartTag();
                      if (_jspx_eval_c_choose_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                        _jspx_th_c_when_4.setTest( Validator.isNotNull(thumbnailSrc) );
                        int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                        if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          //  liferay-ui:search-container-column-image
                          com.liferay.taglib.ui.SearchContainerColumnImageTag _jspx_th_liferay$1ui_search$1container$1column$1image_1 = (com.liferay.taglib.ui.SearchContainerColumnImageTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.get(com.liferay.taglib.ui.SearchContainerColumnImageTag.class);
                          _jspx_th_liferay$1ui_search$1container$1column$1image_1.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_search$1container$1column$1image_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_4);
                          _jspx_th_liferay$1ui_search$1container$1column$1image_1.setSrc( DLUtil.getThumbnailSrc(fileEntry, themeDisplay) );
                          int _jspx_eval_liferay$1ui_search$1container$1column$1image_1 = _jspx_th_liferay$1ui_search$1container$1column$1image_1.doStartTag();
                          if (_jspx_th_liferay$1ui_search$1container$1column$1image_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1image_1);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1image_1);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                        _jspx_th_c_when_5.setTest( (dlMimeTypeDisplayContext != null) && Validator.isNotNull(latestFileVersion.getExtension()) );
                        int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                        if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
                          //  liferay-ui:search-container-column-text
                          com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_10 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_10.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                          int _jspx_eval_liferay$1ui_search$1container$1column$1text_10 = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doStartTag();
                          if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1ui_search$1container$1column$1text_10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1ui_search$1container$1column$1text_10.doInitBody();
                            }
                            do {
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"sticker ");
                              out.print( dlMimeTypeDisplayContext.getCssClassFileMimeType(fileEntry.getMimeType()) );
                              out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                              out.print( StringUtil.shorten(StringUtil.upperCase(latestFileVersion.getExtension()), 3, StringPool.BLANK) );
                              out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t\t");
                              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                            } while (true);
                            if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                          }
                          if (_jspx_th_liferay$1ui_search$1container$1column$1text_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                        out.write("\n\t\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_c_otherwise_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_4, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_choose_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                        return;
                      }
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                      out.write("\n\n\t\t\t\t\t\t\t\t\t");
                      //  liferay-ui:search-container-column-text
                      com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_11 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                      _jspx_th_liferay$1ui_search$1container$1column$1text_11.setColspan( 2 );
                      int _jspx_eval_liferay$1ui_search$1container$1column$1text_11 = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doStartTag();
                      if (_jspx_eval_liferay$1ui_search$1container$1column$1text_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                          out = _jspx_page_context.pushBody();
                          _jspx_th_liferay$1ui_search$1container$1column$1text_11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                          _jspx_th_liferay$1ui_search$1container$1column$1text_11.doInitBody();
                        }
                        do {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t<div class=\"item-preview\" data-href=\"");
                          out.print( Validator.isNotNull(thumbnailSrc) ? HtmlUtil.escapeHREF(DLUtil.getImagePreviewURL(fileEntry, themeDisplay)) : themeDisplay.getPathThemeImages() + "/file_system/large/default.png" );
                          out.write("\" data-metadata=\"");
                          out.print( HtmlUtil.escapeAttribute(itemMedatadaJSONObject.toString()) );
                          out.write("\" data-returnType=\"");
                          out.print( HtmlUtil.escapeAttribute(ItemSelectorRepositoryEntryBrowserUtil.getItemSelectorReturnTypeClassName(itemSelectorReturnTypeResolver, existingFileEntryReturnType)) );
                          out.write("\" data-title=\"");
                          out.print( HtmlUtil.escapeAttribute(title) );
                          out.write("\" data-url=\"");
                          out.print( HtmlUtil.escapeAttribute(DLUtil.getPreviewURL(fileEntry, latestFileVersion, themeDisplay, StringPool.BLANK)) );
                          out.write("\" data-value=\"");
                          out.print( HtmlUtil.escapeAttribute(ItemSelectorRepositoryEntryBrowserUtil.getValue(itemSelectorReturnTypeResolver, existingFileEntryReturnType, fileEntry, themeDisplay)) );
                          out.write("\">\n\t\t\t\t\t\t\t\t\t\t\t");
                          //  liferay-ui:app-view-entry
                          com.liferay.taglib.ui.AppViewEntryTag _jspx_th_liferay$1ui_app$1view$1entry_1 = (com.liferay.taglib.ui.AppViewEntryTag) _jspx_tagPool_liferay$1ui_app$1view$1entry_version_title_status_showCheckbox_modifiedDate_markupView_groupId_displayStyle_description_createDate_author_assetTagClassPK_assetTagClassName_assetCategoryClassPK_assetCategoryClassName_nobody.get(com.liferay.taglib.ui.AppViewEntryTag.class);
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_11);
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setAssetCategoryClassName( DLFileEntry.class.getName() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setAssetCategoryClassPK( fileEntry.getFileEntryId() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setAssetTagClassName( DLFileEntry.class.getName() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setAssetTagClassPK( fileEntry.getFileEntryId() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setAuthor( fileEntry.getUserName() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setCreateDate( fileEntry.getCreateDate() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setDescription( fileEntry.getDescription() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setDisplayStyle("descriptive");
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setGroupId( fileEntry.getGroupId() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setMarkupView("lexicon");
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setModifiedDate( fileEntry.getModifiedDate() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setShowCheckbox( false );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setStatus( latestFileVersion.getStatus() );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setTitle( title );
                          _jspx_th_liferay$1ui_app$1view$1entry_1.setVersion( String.valueOf(fileEntry.getVersion()) );
                          int _jspx_eval_liferay$1ui_app$1view$1entry_1 = _jspx_th_liferay$1ui_app$1view$1entry_1.doStartTag();
                          if (_jspx_th_liferay$1ui_app$1view$1entry_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_app$1view$1entry_version_title_status_showCheckbox_modifiedDate_markupView_groupId_displayStyle_description_createDate_author_assetTagClassPK_assetTagClassName_assetCategoryClassPK_assetCategoryClassName_nobody.reuse(_jspx_th_liferay$1ui_app$1view$1entry_1);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_app$1view$1entry_version_title_status_showCheckbox_modifiedDate_markupView_groupId_displayStyle_description_createDate_author_assetTagClassPK_assetTagClassName_assetCategoryClassPK_assetCategoryClassName_nobody.reuse(_jspx_th_liferay$1ui_app$1view$1entry_1);
                          out.write("\n\t\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t");
                          int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_11.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                        if (_jspx_eval_liferay$1ui_search$1container$1column$1text_11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                          out = _jspx_page_context.popBody();
                      }
                      if (_jspx_th_liferay$1ui_search$1container$1column$1text_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_11);
                      out.write("\n\n\t\t\t\t\t\t\t\t");

								}
								
                      out.write("\n\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              out.write("\n\t\t\t");
              int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              repositoryEntry = (com.liferay.portal.kernel.repository.model.RepositoryEntry) _jspx_page_context.findAttribute("repositoryEntry");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          out.write("\n\n\t\t\t");
          //  liferay-ui:search-iterator
          com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
          _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
          _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle( displayStyle );
          _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
          _jspx_th_liferay$1ui_search$1iterator_0.setResultRowSplitter( new RepositoryEntryResultRowSplitter() );
          _jspx_th_liferay$1ui_search$1iterator_0.setSearchContainer( searchContainer );
          int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
          if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1iterator_searchContainer_resultRowSplitter_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
          out.write("\n\t\t");
        }
        if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container_var_total_searchContainer.reuse(_jspx_th_liferay$1ui_search$1container_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container_var_total_searchContainer.reuse(_jspx_th_liferay$1ui_search$1container_0);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_4.setPageContext(_jspx_page_context);
        _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
        _jspx_th_c_if_4.setTest( !showSearchInfo && (uploadURL != null) );
        int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
        if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          if (_jspx_meth_liferay$1ui_drop$1here$1info_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
            return;
          out.write("\n\t\t");
        }
        if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      out.write("\n</div>\n\n");
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setUse("liferay-item-selector-repository-entry-browser");
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tnew Liferay.ItemSelectorRepositoryEntryBrowser(\n\t\t{\n\t\t\tcloseCaption: '");
          out.print( UnicodeLanguageUtil.get(request, tabName) );
          out.write("',\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_5.setPageContext(_jspx_page_context);
          _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_5.setTest( uploadURL != null );
          int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
          if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t\t");

				String imageEditorPortletId = PortletProviderUtil.getPortletId(Image.class.getName(), PortletProvider.Action.EDIT);
				
            out.write("\n\n\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_6.setPageContext(_jspx_page_context);
            _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
            _jspx_th_c_if_6.setTest( Validator.isNotNull(imageEditorPortletId) );
            int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
            if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t");
              //  liferay-portlet:renderURL
              com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.get(com.liferay.taglib.portlet.RenderURLTag.class);
              _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
              _jspx_th_liferay$1portlet_renderURL_0.setPortletName( imageEditorPortletId );
              _jspx_th_liferay$1portlet_renderURL_0.setVar("viewImageEditorURL");
              _jspx_th_liferay$1portlet_renderURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
              int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
              if (_jspx_eval_liferay$1portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t");
                if (_jspx_meth_liferay$1portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1portlet_renderURL_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t");
              }
              if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_0);
                return;
              }
              _jspx_tagPool_liferay$1portlet_renderURL_windowState_var_portletName.reuse(_jspx_th_liferay$1portlet_renderURL_0);
              java.lang.String viewImageEditorURL = null;
              viewImageEditorURL = (java.lang.String) _jspx_page_context.findAttribute("viewImageEditorURL");
              out.write("\n\n\t\t\t\t\teditItemURL: '");
              out.print( viewImageEditorURL.toString() );
              out.write("',\n\t\t\t\t");
            }
            if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
            out.write("\n\t\t\t");
          }
          if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
          out.write("\n\n\t\t\tmaxFileSize: '");
          out.print( maxFileSize );
          out.write("',\n\t\t\ton: {\n\t\t\t\tselectedItem: function(event) {\n\t\t\t\t\tLiferay.Util.getOpener().Liferay.fire('");
          out.print( itemSelectedEventName );
          out.write("', event);\n\t\t\t\t}\n\t\t\t},\n\t\t\trootNode: '#");
          out.print( randomNamespace );
          out.write("ItemSelectorContainer',\n\t\t\tvalidExtensions: '");
          out.print( ListUtil.isEmpty(extensions) ? "*" : StringUtil.merge(extensions) );
          out.write("'\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_7.setTest( uploadURL != null );
          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t\t");

				String returnType = ItemSelectorRepositoryEntryBrowserUtil.getItemSelectorReturnTypeClassName(itemSelectorReturnTypeResolver, existingFileEntryReturnType);

				uploadURL.setParameter("returnType", returnType);
				
            out.write("\n\n\t\t\t\t, uploadItemReturnType: '");
            out.print( HtmlUtil.escapeAttribute(returnType) );
            out.write("',\n\t\t\t\tuploadItemURL: '");
            out.print( uploadURL.toString() );
            out.write("'\n\t\t\t");
          }
          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          out.write("\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
        return;
      }
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1util_buffer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_0);
    _jspx_th_liferay$1ui_message_0.setKey("select-file");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("size");
    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setValue("--");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1text_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-text
    com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("status");
    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue("--");
    int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1frontend_horizontal$1card$1col_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_horizontal$1card_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-frontend:horizontal-card-col
    com.liferay.frontend.taglib.servlet.taglib.HorizontalCardColTag _jspx_th_liferay$1frontend_horizontal$1card$1col_0 = (com.liferay.frontend.taglib.servlet.taglib.HorizontalCardColTag) _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.get(com.liferay.frontend.taglib.servlet.taglib.HorizontalCardColTag.class);
    _jspx_th_liferay$1frontend_horizontal$1card$1col_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1frontend_horizontal$1card$1col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_horizontal$1card_0);
    int _jspx_eval_liferay$1frontend_horizontal$1card$1col_0 = _jspx_th_liferay$1frontend_horizontal$1card$1col_0.doStartTag();
    if (_jspx_eval_liferay$1frontend_horizontal$1card$1col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1frontend_horizontal$1card$1col_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1frontend_horizontal$1card$1col_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1frontend_horizontal$1card$1col_0.doInitBody();
      }
      do {
        out.write("\n\t\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_liferay$1frontend_horizontal$1card$1icon_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_horizontal$1card$1col_0, _jspx_page_context))
          return true;
        out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_liferay$1frontend_horizontal$1card$1col_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1frontend_horizontal$1card$1col_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1frontend_horizontal$1card$1col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1col_0);
      return true;
    }
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1col.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1col_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1frontend_horizontal$1card$1icon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_horizontal$1card$1col_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-frontend:horizontal-card-icon
    com.liferay.frontend.taglib.servlet.taglib.HorizontalCardIconTag _jspx_th_liferay$1frontend_horizontal$1card$1icon_0 = (com.liferay.frontend.taglib.servlet.taglib.HorizontalCardIconTag) _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.get(com.liferay.frontend.taglib.servlet.taglib.HorizontalCardIconTag.class);
    _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_horizontal$1card$1col_0);
    _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.setIcon("folder");
    int _jspx_eval_liferay$1frontend_horizontal$1card$1icon_0 = _jspx_th_liferay$1frontend_horizontal$1card$1icon_0.doStartTag();
    if (_jspx_th_liferay$1frontend_horizontal$1card$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1icon_0);
      return true;
    }
    _jspx_tagPool_liferay$1frontend_horizontal$1card$1icon_icon_nobody.reuse(_jspx_th_liferay$1frontend_horizontal$1card$1icon_0);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_4 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_4.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
    int _jspx_eval_c_otherwise_4 = _jspx_th_c_otherwise_4.doStartTag();
    if (_jspx_eval_c_otherwise_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_search$1container$1column$1icon_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_4, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1icon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-icon
    com.liferay.taglib.ui.SearchContainerColumnIconTag _jspx_th_liferay$1ui_search$1container$1column$1icon_0 = (com.liferay.taglib.ui.SearchContainerColumnIconTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.get(com.liferay.taglib.ui.SearchContainerColumnIconTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_4);
    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setIcon("documents-and-media");
    int _jspx_eval_liferay$1ui_search$1container$1column$1icon_0 = _jspx_th_liferay$1ui_search$1container$1column$1icon_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_drop$1here$1info_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:drop-here-info
    com.liferay.taglib.ui.DropHereInfoTag _jspx_th_liferay$1ui_drop$1here$1info_0 = (com.liferay.taglib.ui.DropHereInfoTag) _jspx_tagPool_liferay$1ui_drop$1here$1info_message_nobody.get(com.liferay.taglib.ui.DropHereInfoTag.class);
    _jspx_th_liferay$1ui_drop$1here$1info_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_drop$1here$1info_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_liferay$1ui_drop$1here$1info_0.setMessage("drop-files-here");
    int _jspx_eval_liferay$1ui_drop$1here$1info_0 = _jspx_th_liferay$1ui_drop$1here$1info_0.doStartTag();
    if (_jspx_th_liferay$1ui_drop$1here$1info_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_drop$1here$1info_message_nobody.reuse(_jspx_th_liferay$1ui_drop$1here$1info_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_drop$1here$1info_message_nobody.reuse(_jspx_th_liferay$1ui_drop$1here$1info_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_liferay$1portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_liferay$1portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_liferay$1portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1portlet_renderURL_0);
    _jspx_th_liferay$1portlet_param_0.setName("mvcRenderCommandName");
    _jspx_th_liferay$1portlet_param_0.setValue("/image_editor/view");
    int _jspx_eval_liferay$1portlet_param_0 = _jspx_th_liferay$1portlet_param_0.doStartTag();
    if (_jspx_th_liferay$1portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_0);
      return true;
    }
    _jspx_tagPool_liferay$1portlet_param_value_name_nobody.reuse(_jspx_th_liferay$1portlet_param_0);
    return false;
  }
}
