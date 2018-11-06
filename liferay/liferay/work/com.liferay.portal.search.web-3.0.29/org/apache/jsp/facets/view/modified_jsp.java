package org.apache.jsp.facets.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.search.web.constants.SearchPortletKeys;
import com.liferay.portal.search.web.constants.SearchPortletParameterNames;
import com.liferay.portal.search.web.facet.SearchFacet;
import com.liferay.portal.search.web.facet.util.comparator.SearchFacetComparator;
import com.liferay.portal.search.web.internal.display.context.SearchDisplayContext;
import com.liferay.portal.search.web.internal.display.context.SearchDisplayContextFactoryUtil;
import com.liferay.portal.search.web.internal.facet.AssetEntriesSearchFacet;
import com.liferay.portal.search.web.internal.result.display.context.SearchResultFieldDisplayContext;
import com.liferay.portal.search.web.internal.search.suggest.KeywordsSuggestionHolder;
import com.liferay.taglib.aui.AUIUtil;
import com.liferay.taglib.servlet.PipingServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.portlet.PortletMode;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.search.web.internal.facet.display.builder.AssetCategoriesSearchFacetDisplayBuilder;
import com.liferay.portal.search.web.internal.facet.display.builder.AssetCategoryPermissionCheckerImpl;
import com.liferay.portal.search.web.internal.facet.display.builder.AssetEntriesSearchFacetDisplayBuilder;
import com.liferay.portal.search.web.internal.facet.display.builder.AssetTagsSearchFacetDisplayBuilder;
import com.liferay.portal.search.web.internal.facet.display.builder.FolderSearchFacetDisplayBuilder;
import com.liferay.portal.search.web.internal.facet.display.builder.ScopeSearchFacetDisplayBuilder;
import com.liferay.portal.search.web.internal.facet.display.builder.UserSearchFacetDisplayBuilder;
import com.liferay.portal.search.web.internal.facet.display.context.AssetCategoriesSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.AssetCategoriesSearchFacetTermDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.AssetEntriesSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.AssetEntriesSearchFacetTermDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetTermDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.FolderSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.FolderSearchFacetTermDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.FolderTitleLookupImpl;
import com.liferay.portal.search.web.internal.facet.display.context.ScopeSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.ScopeSearchFacetTermDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetTermDisplayContext;

public final class modified_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private static final String _RANDOM_KEY_INPUT = "portlet_search_facets_" + StringUtil.randomString();

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/facets/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_name_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_name_label_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href_data_cssClass;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_name_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_name_label_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href_data_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_field$1wrapper_label.release();
    _jspx_tagPool_aui_button_value_onClick_name_disabled_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_name_label_cssClass.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_aui_a_href_cssClass.release();
    _jspx_tagPool_aui_a_href_data_cssClass.release();
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
      out.write('\n');
      out.write('\n');

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

SearchDisplayContext searchDisplayContext = (SearchDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

if (searchDisplayContext == null) {
	searchDisplayContext = SearchDisplayContextFactoryUtil.create(renderRequest, renderResponse, portletPreferences);
}

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n");

String randomNamespace = PortalUtil.generateRandomKey(request, _RANDOM_KEY_INPUT) + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search.jsp-facet");

String fieldParam = ParamUtil.getString(request, facet.getFieldId());

FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

JSONObject dataJSONObject = facetConfiguration.getData();

FacetCollector facetCollector = facet.getFacetCollector();

String cssClass = "search-facet search-".concat(HtmlUtil.escapeAttribute(facetConfiguration.getDisplayStyle()));

      out.write('\n');
      out.write('\n');
      out.write("\n\n<style>\n\t.facet-term-selected {\n\t\tfont-weight: 600;\n\t}\n\n\t.facet-term-unselected {\n\t\tfont-weight: 400;\n\t}\n</style>");
      out.write('\n');
      out.write('\n');

String fieldParamSelection = ParamUtil.getString(request, facet.getFieldId() + "selection", "0");

String fieldParamFrom = ParamUtil.getString(request, facet.getFieldId() + "from");
String fieldParamTo = ParamUtil.getString(request, facet.getFieldId() + "to");

Date fromDate = null;
Date toDate = null;

if (Validator.isNotNull(fieldParamFrom) || Validator.isNotNull(fieldParamTo)) {
	String delimiter = StringPool.FORWARD_SLASH;

	int dayPosition = 1;
	int monthPosition = 0;
	int yearPosition = 2;

	if (BrowserSnifferUtil.isMobile(request)) {
		delimiter = StringPool.DASH;

		dayPosition = 2;
		monthPosition = 1;
		yearPosition = 0;
	}

	String[] from = StringUtil.split(fieldParamFrom, delimiter);

	if (ArrayUtil.isNotEmpty(from) && (from.length == 3)) {
		int fromDay = GetterUtil.getInteger(from[dayPosition]);
		int fromMonth = GetterUtil.getInteger(from[monthPosition]) - 1;
		int fromYear = GetterUtil.getInteger(from[yearPosition]);

		fromDate = PortalUtil.getDate(fromMonth, fromDay, fromYear);
	}

	String[] to = StringUtil.split(fieldParamTo, delimiter);

	if (ArrayUtil.isNotEmpty(to) && (to.length == 3)) {
		int toDay = GetterUtil.getInteger(to[dayPosition]);
		int toMonth = GetterUtil.getInteger(to[monthPosition]) - 1;
		int toYear = GetterUtil.getInteger(to[yearPosition]);

		toDate = PortalUtil.getDate(toMonth, toDay, toYear);
	}
}

JSONArray rangesJSONArray = dataJSONObject.getJSONArray("ranges");

int index = 0;

      out.write("\n\n<div class=\"panel panel-default\">\n\t<div class=\"panel-heading\">\n\t\t<div class=\"panel-title\">\n\t\t\t");
      if (_jspx_meth_liferay$1ui_message_0(_jspx_page_context))
        return;
      out.write("\n\t\t</div>\n\t</div>\n\n\t<div class=\"panel-body\">\n\t\t<div class=\"");
      out.print( cssClass );
      out.write("\" data-facetFieldName=\"");
      out.print( HtmlUtil.escapeAttribute(facet.getFieldId()) );
      out.write("\" id=\"");
      out.print( randomNamespace );
      out.write("facet\">\n\t\t\t");
      //  aui:input
      com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.get(com.liferay.taglib.aui.InputTag.class);
      _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_input_0.setParent(null);
      _jspx_th_aui_input_0.setDynamicAttribute(null, "autocomplete", new String("off"));
      _jspx_th_aui_input_0.setName( HtmlUtil.escapeAttribute(facet.getFieldId()) );
      _jspx_th_aui_input_0.setType("hidden");
      _jspx_th_aui_input_0.setValue( fieldParam );
      int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
      if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.reuse(_jspx_th_aui_input_0);
        return;
      }
      _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.reuse(_jspx_th_aui_input_0);
      out.write("\n\t\t\t");
      //  aui:input
      com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.get(com.liferay.taglib.aui.InputTag.class);
      _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
      _jspx_th_aui_input_1.setParent(null);
      _jspx_th_aui_input_1.setDynamicAttribute(null, "autocomplete", new String("off"));
      _jspx_th_aui_input_1.setName( HtmlUtil.escapeAttribute(facet.getFieldId()) + "selection" );
      _jspx_th_aui_input_1.setType("hidden");
      _jspx_th_aui_input_1.setValue( fieldParamSelection );
      int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
      if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.reuse(_jspx_th_aui_input_1);
        return;
      }
      _jspx_tagPool_aui_input_value_type_name_autocomplete_nobody.reuse(_jspx_th_aui_input_1);
      out.write("\n\n\t\t\t");
      //  aui:field-wrapper
      com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_name_label_cssClass.get(com.liferay.taglib.aui.FieldWrapperTag.class);
      _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_field$1wrapper_0.setParent(null);
      _jspx_th_aui_field$1wrapper_0.setCssClass( randomNamespace + "calendar calendar_" );
      _jspx_th_aui_field$1wrapper_0.setLabel("");
      _jspx_th_aui_field$1wrapper_0.setName( HtmlUtil.escapeAttribute(facet.getFieldId()) );
      int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
      if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t\t<ul class=\"list-unstyled modified\">\n\t\t\t\t\t<li class=\"default facet-value\">\n\n\t\t\t\t\t\t");

						Map<String, Object> data = new HashMap<>();

						data.put("selection", 0);
						data.put("value", StringPool.BLANK);
						
        out.write("\n\n\t\t\t\t\t\t");
        //  aui:a
        com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href_cssClass.get(com.liferay.taglib.aui.ATag.class);
        _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
        _jspx_th_aui_a_0.setCssClass( (Validator.isNull(fieldParamSelection) || fieldParamSelection.equals("0")) ? "facet-term-selected" : "facet-term-unselected" );
        _jspx_th_aui_a_0.setHref("javascript:;");
        int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
        if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t\t\t\t");
          //  liferay-ui:message
          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
          _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_0);
          _jspx_th_liferay$1ui_message_1.setKey( HtmlUtil.escape(facetConfiguration.getLabel()) );
          int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
          if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
          out.write("\n\t\t\t\t\t\t");
        }
        if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_0);
          return;
        }
        _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_0);
        out.write("\n\t\t\t\t\t</li>\n\n\t\t\t\t\t");

					for (int i = 0; i < rangesJSONArray.length(); i++) {
						JSONObject rangesJSONObject = rangesJSONArray.getJSONObject(i);

						String label = HtmlUtil.escape(rangesJSONObject.getString("label"));
						String range = rangesJSONObject.getString("range");

						index = (i + 1);
					
        out.write("\n\n\t\t\t\t\t\t<li class=\"facet-value\">\n\n\t\t\t\t\t\t\t");

							String rangeCssClass = "facet-term-unselected";

							if (fieldParamSelection.equals(String.valueOf(index))) {
								rangeCssClass = "facet-term-selected";
							}

							data = new HashMap<>();

							data.put("selection", index);
							data.put("value", HtmlUtil.escape(range));
							
        out.write("\n\n\t\t\t\t\t\t\t");
        //  aui:a
        com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href_data_cssClass.get(com.liferay.taglib.aui.ATag.class);
        _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
        _jspx_th_aui_a_1.setCssClass( rangeCssClass );
        _jspx_th_aui_a_1.setData( data );
        _jspx_th_aui_a_1.setHref("javascript:;");
        int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
        if (_jspx_eval_aui_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t\t\t\t\t");
          //  liferay-ui:message
          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
          _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_1);
          _jspx_th_liferay$1ui_message_2.setKey( label );
          int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
          if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
          out.write("\n\n\t\t\t\t\t\t\t\t");

								TermCollector termCollector = facetCollector.getTermCollector(range);
								
          out.write("\n\n\t\t\t\t\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_1);
          _jspx_th_c_if_0.setTest( termCollector != null );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\t\t\t\t<span class=\"frequency\">(");
            out.print( termCollector.getFrequency() );
            out.write(")</span>\n\t\t\t\t\t\t\t\t");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\t\t\t\t\t\t\t");
        }
        if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_a_href_data_cssClass.reuse(_jspx_th_aui_a_1);
          return;
        }
        _jspx_tagPool_aui_a_href_data_cssClass.reuse(_jspx_th_aui_a_1);
        out.write("\n\t\t\t\t\t\t</li>\n\n\t\t\t\t\t");

					}
					
        out.write("\n\n\t\t\t\t\t<li class=\"facet-value\">\n\n\t\t\t\t\t\t");

						String customRangeCssClass = randomNamespace + "custom-range-toggle";

						if (fieldParamSelection.equals(String.valueOf(index + 1))) {
							customRangeCssClass += " facet-term-selected";
						}
						else {
							customRangeCssClass += " facet-term-unselected";
						}

						TermCollector termCollector = null;

						if (fieldParamSelection.equals(String.valueOf(index + 1))) {
							termCollector = facetCollector.getTermCollector(fieldParam);
						}
						
        out.write("\n\n\t\t\t\t\t\t");
        //  aui:a
        com.liferay.taglib.aui.ATag _jspx_th_aui_a_2 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href_cssClass.get(com.liferay.taglib.aui.ATag.class);
        _jspx_th_aui_a_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
        _jspx_th_aui_a_2.setCssClass( customRangeCssClass );
        _jspx_th_aui_a_2.setHref("javascript:;");
        int _jspx_eval_aui_a_2 = _jspx_th_aui_a_2.doStartTag();
        if (_jspx_eval_aui_a_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t\t\t\t");
          if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_a_2, _jspx_page_context))
            return;
          out.write("&hellip;\n\n\t\t\t\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_1.setPageContext(_jspx_page_context);
          _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_2);
          _jspx_th_c_if_1.setTest( termCollector != null );
          int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
          if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\t\t\t<span class=\"frequency\">(");
            out.print( termCollector.getFrequency() );
            out.write(")</span>\n\t\t\t\t\t\t\t");
          }
          if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          out.write("\n\t\t\t\t\t\t");
        }
        if (_jspx_th_aui_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_2);
          return;
        }
        _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_2);
        out.write("\n\t\t\t\t\t</li>\n\n\t\t\t\t\t");

					Calendar fromCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

					if (Validator.isNotNull(fromDate)) {
						fromCalendar.setTime(fromDate);
					}
					else {
						fromCalendar.add(Calendar.DATE, -1);
					}

					Calendar toCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

					if (Validator.isNotNull(toDate)) {
						toCalendar.setTime(toDate);
					}
					
        out.write("\n\n\t\t\t\t\t<div class=\"");
        out.print( !fieldParamSelection.equals(String.valueOf(index + 1)) ? "hide" : StringPool.BLANK );
        out.write(" modified-custom-range\" id=\"");
        out.print( randomNamespace );
        out.write("customRange\">\n\t\t\t\t\t\t<div class=\"col-md-6\" id=\"");
        out.print( randomNamespace );
        out.write("customRangeFrom\">\n\t\t\t\t\t\t\t");
        //  aui:field-wrapper
        com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_1 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
        _jspx_th_aui_field$1wrapper_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_field$1wrapper_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
        _jspx_th_aui_field$1wrapper_1.setLabel("from");
        int _jspx_eval_aui_field$1wrapper_1 = _jspx_th_aui_field$1wrapper_1.doStartTag();
        if (_jspx_eval_aui_field$1wrapper_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t\t\t\t\t");
          //  liferay-ui:input-date
          com.liferay.taglib.ui.InputDateTag _jspx_th_liferay$1ui_input$1date_0 = (com.liferay.taglib.ui.InputDateTag) _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.get(com.liferay.taglib.ui.InputDateTag.class);
          _jspx_th_liferay$1ui_input$1date_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_input$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_1);
          _jspx_th_liferay$1ui_input$1date_0.setDayParam( HtmlUtil.escapeJS(facet.getFieldId()) + "dayFrom" );
          _jspx_th_liferay$1ui_input$1date_0.setDayValue( fromCalendar.get(Calendar.DATE) );
          _jspx_th_liferay$1ui_input$1date_0.setDisabled( false );
          _jspx_th_liferay$1ui_input$1date_0.setFirstDayOfWeek( fromCalendar.getFirstDayOfWeek() - 1 );
          _jspx_th_liferay$1ui_input$1date_0.setMonthParam( HtmlUtil.escapeJS(facet.getFieldId()) + "monthFrom" );
          _jspx_th_liferay$1ui_input$1date_0.setMonthValue( fromCalendar.get(Calendar.MONTH) );
          _jspx_th_liferay$1ui_input$1date_0.setName( HtmlUtil.escapeJS(facet.getFieldId()) + "from" );
          _jspx_th_liferay$1ui_input$1date_0.setYearParam( HtmlUtil.escapeJS(facet.getFieldId()) + "yearFrom" );
          _jspx_th_liferay$1ui_input$1date_0.setYearValue( fromCalendar.get(Calendar.YEAR) );
          int _jspx_eval_liferay$1ui_input$1date_0 = _jspx_th_liferay$1ui_input$1date_0.doStartTag();
          if (_jspx_th_liferay$1ui_input$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_0);
          out.write("\n\t\t\t\t\t\t\t");
        }
        if (_jspx_th_aui_field$1wrapper_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
          return;
        }
        _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
        out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t<div class=\"col-md-6\" id=\"");
        out.print( randomNamespace );
        out.write("customRangeTo\">\n\t\t\t\t\t\t\t");
        //  aui:field-wrapper
        com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_2 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
        _jspx_th_aui_field$1wrapper_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_field$1wrapper_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
        _jspx_th_aui_field$1wrapper_2.setLabel("to");
        int _jspx_eval_aui_field$1wrapper_2 = _jspx_th_aui_field$1wrapper_2.doStartTag();
        if (_jspx_eval_aui_field$1wrapper_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t\t\t\t\t");
          //  liferay-ui:input-date
          com.liferay.taglib.ui.InputDateTag _jspx_th_liferay$1ui_input$1date_1 = (com.liferay.taglib.ui.InputDateTag) _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.get(com.liferay.taglib.ui.InputDateTag.class);
          _jspx_th_liferay$1ui_input$1date_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_input$1date_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_2);
          _jspx_th_liferay$1ui_input$1date_1.setDayParam( HtmlUtil.escapeJS(facet.getFieldId()) + "dayTo" );
          _jspx_th_liferay$1ui_input$1date_1.setDayValue( toCalendar.get(Calendar.DATE) );
          _jspx_th_liferay$1ui_input$1date_1.setDisabled( false );
          _jspx_th_liferay$1ui_input$1date_1.setFirstDayOfWeek( toCalendar.getFirstDayOfWeek() - 1 );
          _jspx_th_liferay$1ui_input$1date_1.setMonthParam( HtmlUtil.escapeJS(facet.getFieldId()) + "monthTo" );
          _jspx_th_liferay$1ui_input$1date_1.setMonthValue( toCalendar.get(Calendar.MONTH) );
          _jspx_th_liferay$1ui_input$1date_1.setName( HtmlUtil.escapeJS(facet.getFieldId()) + "to" );
          _jspx_th_liferay$1ui_input$1date_1.setYearParam( HtmlUtil.escapeJS(facet.getFieldId()) + "yearTo" );
          _jspx_th_liferay$1ui_input$1date_1.setYearValue( toCalendar.get(Calendar.YEAR) );
          int _jspx_eval_liferay$1ui_input$1date_1 = _jspx_th_liferay$1ui_input$1date_1.doStartTag();
          if (_jspx_th_liferay$1ui_input$1date_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_1);
          out.write("\n\t\t\t\t\t\t\t");
        }
        if (_jspx_th_aui_field$1wrapper_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_2);
          return;
        }
        _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_2);
        out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");

						String taglibSearchCustomRange = "window['" + renderResponse.getNamespace() + HtmlUtil.escapeJS(facet.getFieldId()) + "searchCustomRange'](" + (index + 1) + ");";
						
        out.write("\n\n\t\t\t\t\t\t");
        //  aui:button
        com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_name_disabled_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
        _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
        _jspx_th_aui_button_0.setDisabled( toCalendar.getTimeInMillis() < fromCalendar.getTimeInMillis() );
        _jspx_th_aui_button_0.setName("searchCustomRangeButton");
        _jspx_th_aui_button_0.setOnClick( taglibSearchCustomRange );
        _jspx_th_aui_button_0.setValue("search");
        int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
        if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button_value_onClick_name_disabled_nobody.reuse(_jspx_th_aui_button_0);
          return;
        }
        _jspx_tagPool_aui_button_value_onClick_name_disabled_nobody.reuse(_jspx_th_aui_button_0);
        out.write("\n\t\t\t\t\t</div>\n\t\t\t\t</ul>\n\t\t\t");
      }
      if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_field$1wrapper_name_label_cssClass.reuse(_jspx_th_aui_field$1wrapper_0);
        return;
      }
      _jspx_tagPool_aui_field$1wrapper_name_label_cssClass.reuse(_jspx_th_aui_field$1wrapper_0);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n");
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tvar form = AUI.$(document.");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("fm);\n\n\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("dayFrom').prop('disabled', true);\n\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("monthFrom').prop('disabled', true);\n\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("yearFrom').prop('disabled', true);\n\n\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("dayTo').prop('disabled', true);\n\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("monthTo').prop('disabled', true);\n\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("yearTo').prop('disabled', true);\n\n\tfunction ");
          if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("searchCustomRange(selection) {\n\t\tvar A = AUI();\n\t\tvar Lang = A.Lang;\n\t\tvar LString = Lang.String;\n\n\t\tvar dayFrom = form.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("dayFrom').val();\n\t\tvar monthFrom = Lang.toInt(form.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("monthFrom').val()) + 1;\n\t\tvar yearFrom = form.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("yearFrom').val();\n\n\t\tvar dayTo = form.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("dayTo').val();\n\t\tvar monthTo = Lang.toInt(form.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("monthTo').val()) + 1;\n\t\tvar yearTo = form.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("yearTo').val();\n\n\t\tvar range = '[' + yearFrom + LString.padNumber(monthFrom, 2) + LString.padNumber(dayFrom, 2) + '000000 TO ' + yearTo + LString.padNumber(monthTo, 2) + LString.padNumber(dayTo, 2) + '235959]';\n\n\t\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("').val(range);\n\t\tform.fm('");
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("selection').val(selection);\n\n\t\tsubmitForm(form);\n\t}\n");
          int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
        return;
      }
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_1.setParent(null);
      _jspx_th_aui_script_1.setUse("aui-form-validator");
      int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_1.doInitBody();
        }
        do {
          out.write("\n\tvar Util = Liferay.Util;\n\n\tvar customRangeFrom = Liferay.component('");
          out.print( renderResponse.getNamespace() );
          out.write("modifiedfromDatePicker');\n\tvar customRangeTo = Liferay.component('");
          out.print( renderResponse.getNamespace() );
          out.write("modifiedtoDatePicker');\n\tvar searchButton = A.one('#");
          if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("searchCustomRangeButton');\n\n\tvar preventKeyboardDateChange = function(event) {\n\t\tif (!event.isKey('TAB')) {\n\t\t\tevent.preventDefault();\n\t\t}\n\t};\n\n\tA.one('#");
          if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("from').on('keydown', preventKeyboardDateChange);\n\tA.one('#");
          if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("to').on('keydown', preventKeyboardDateChange);\n\n\tvar DEFAULTS_FORM_VALIDATOR = A.config.FormValidator;\n\n\tA.mix(\n\t\tDEFAULTS_FORM_VALIDATOR.STRINGS,\n\t\t{\n\t\t\t");
          if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("dateRange: '");
          out.print( UnicodeLanguageUtil.get(request, "search-custom-range-invalid-date-range") );
          out.write("'\n\t\t},\n\t\ttrue\n\t);\n\n\tA.mix(\n\t\tDEFAULTS_FORM_VALIDATOR.RULES,\n\t\t{\n\t\t\t");
          if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("dateRange: function(val, fieldNode, ruleValue) {\n\t\t\t\treturn A.Date.isGreaterOrEqual(customRangeTo.getDate(), customRangeFrom.getDate());\n\t\t\t}\n\t\t},\n\t\ttrue\n\t);\n\n\tvar customRangeValidator = new A.FormValidator(\n\t\t{\n\t\t\tboundingBox: document.");
          if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("fm,\n\t\t\tfieldContainer: 'div',\n\t\t\ton: {\n\t\t\t\terrorField: function(event) {\n\t\t\t\t\tUtil.toggleDisabled(searchButton, true);\n\t\t\t\t},\n\t\t\t\tvalidField: function(event) {\n\t\t\t\t\tUtil.toggleDisabled(searchButton, false);\n\t\t\t\t}\n\t\t\t},\n\t\t\trules: {\n\t\t\t\t'");
          if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("from': {\n\t\t\t\t\t");
          if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("dateRange: true\n\t\t\t\t},\n\t\t\t\t'");
          if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.print( HtmlUtil.escapeJS(facet.getFieldId()) );
          out.write("to': {\n\t\t\t\t\t");
          if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("dateRange: true\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t);\n\n\tvar onRangeSelectionChange = function(event) {\n\t\tcustomRangeValidator.validate();\n\t};\n\n\tcustomRangeFrom.on('selectionChange', onRangeSelectionChange);\n\tcustomRangeTo.on('selectionChange', onRangeSelectionChange);\n\n\tA.one('.");
          out.print( randomNamespace );
          out.write("custom-range-toggle').on(\n\t\t'click',\n\t\tfunction(event) {\n\t\t\tevent.halt();\n\n\t\t\tA.one('#");
          out.print( randomNamespace + "customRange" );
          out.write("').toggle();\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_1);
        return;
      }
      _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_1);
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

  private boolean _jspx_meth_liferay$1ui_message_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent(null);
    _jspx_th_liferay$1ui_message_0.setKey("time");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_a_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_2);
    _jspx_th_liferay$1ui_message_3.setKey("custom-range");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }
}
