package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.document.library.item.selector.web.internal.constants.DLItemSelectorWebKeys;
import com.liferay.document.library.item.selector.web.internal.display.context.DLItemSelectorViewDisplayContext;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.dao.search.SearchPaginationUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchResult;
import com.liferay.portal.kernel.search.SearchResultUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import java.util.ArrayList;
import java.util.List;

public final class documents_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1item$1selector_repository$1entry$1browser_uploadURL_tabName_showBreadcrumb_repositoryEntriesCount_repositoryEntries_portletURL_maxFileSize_itemSelectorReturnTypeResolver_itemSelectedEventName_extensions_emptyResultsMessage_dlMimeTypeDisplayContext_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1item$1selector_repository$1entry$1browser_uploadURL_tabName_showBreadcrumb_repositoryEntriesCount_repositoryEntries_portletURL_maxFileSize_itemSelectorReturnTypeResolver_itemSelectedEventName_extensions_emptyResultsMessage_dlMimeTypeDisplayContext_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1item$1selector_repository$1entry$1browser_uploadURL_tabName_showBreadcrumb_repositoryEntriesCount_repositoryEntries_portletURL_maxFileSize_itemSelectorReturnTypeResolver_itemSelectedEventName_extensions_emptyResultsMessage_dlMimeTypeDisplayContext_nobody.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n");
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

DLItemSelectorViewDisplayContext dlItemSelectorViewDisplayContext = (DLItemSelectorViewDisplayContext)request.getAttribute(DLItemSelectorWebKeys.DL_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
DLMimeTypeDisplayContext dlMimeTypeDisplayContext = (DLMimeTypeDisplayContext)request.getAttribute(DLItemSelectorWebKeys.DL_MIME_TYPE_DISPLAY_CONTEXT);

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR);
int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM, SearchContainer.DEFAULT_DELTA);

int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(cur, delta);

int start = startAndEnd[0];
int end = startAndEnd[1];

List repositoryEntries = null;
int repositoryEntriesCount = 0;

long groupId = dlItemSelectorViewDisplayContext.getStagingAwareGroupId(themeDisplay.getScopeGroupId());
long folderId = dlItemSelectorViewDisplayContext.getFolderId(request);
String[] mimeTypes = dlItemSelectorViewDisplayContext.getMimeTypes();

if (dlItemSelectorViewDisplayContext.isSearch()) {
	SearchContext searchContext = SearchContextFactory.getInstance(request);

	searchContext.setAttribute("mimeTypes", mimeTypes);
	searchContext.setEnd(end);
	searchContext.setFolderIds(new long[] {dlItemSelectorViewDisplayContext.getFolderId(request)});
	searchContext.setGroupIds(new long[] {groupId});
	searchContext.setStart(start);

	Hits hits = DLAppServiceUtil.search(groupId, searchContext);

	repositoryEntriesCount = hits.getLength();

	Document[] docs = hits.getDocs();

	repositoryEntries = new ArrayList(docs.length);

	List<SearchResult> searchResults = SearchResultUtil.getSearchResults(hits, locale);

	for (int i = 0; i < searchResults.size(); i++) {
		SearchResult searchResult = searchResults.get(i);

		String className = searchResult.getClassName();

		if (className.equals(DLFileEntryConstants.getClassName()) || FileEntry.class.isAssignableFrom(Class.forName(className))) {
			repositoryEntries.add(DLAppServiceUtil.getFileEntry(searchResult.getClassPK()));
		}
		else if (className.equals(DLFileShortcutConstants.getClassName())) {
			repositoryEntries.add(DLAppServiceUtil.getFileShortcut(searchResult.getClassPK()));
		}
		else if (className.equals(DLFolderConstants.getClassName())) {
			repositoryEntries.add(DLAppServiceUtil.getFolder(searchResult.getClassPK()));
		}
		else {
			continue;
		}
	}
}
else {
	String orderByCol = ParamUtil.getString(request, "orderByCol", "title");
	String orderByType = ParamUtil.getString(request, "orderByType", "asc");

	repositoryEntries = DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(groupId, folderId, WorkflowConstants.STATUS_APPROVED, mimeTypes, false, false, start, end, DLUtil.getRepositoryModelOrderByComparator(orderByCol, orderByType, true));
	repositoryEntriesCount = DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(groupId, folderId, WorkflowConstants.STATUS_APPROVED, mimeTypes, false, false);
}

      out.write('\n');
      out.write('\n');
      //  liferay-item-selector:repository-entry-browser
      com.liferay.item.selector.taglib.servlet.taglib.RepositoryEntryBrowserTag _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0 = (com.liferay.item.selector.taglib.servlet.taglib.RepositoryEntryBrowserTag) _jspx_tagPool_liferay$1item$1selector_repository$1entry$1browser_uploadURL_tabName_showBreadcrumb_repositoryEntriesCount_repositoryEntries_portletURL_maxFileSize_itemSelectorReturnTypeResolver_itemSelectedEventName_extensions_emptyResultsMessage_dlMimeTypeDisplayContext_nobody.get(com.liferay.item.selector.taglib.servlet.taglib.RepositoryEntryBrowserTag.class);
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setParent(null);
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setDlMimeTypeDisplayContext( dlMimeTypeDisplayContext );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setEmptyResultsMessage( LanguageUtil.get(request, "there-are-no-documents-or-media-files-in-this-folder") );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setExtensions( ListUtil.toList(dlItemSelectorViewDisplayContext.getExtensions()) );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setItemSelectedEventName( dlItemSelectorViewDisplayContext.getItemSelectedEventName() );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setItemSelectorReturnTypeResolver( dlItemSelectorViewDisplayContext.getItemSelectorReturnTypeResolver() );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setMaxFileSize( DLValidatorUtil.getMaxAllowableSize() );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setPortletURL( dlItemSelectorViewDisplayContext.getPortletURL(request, liferayPortletResponse) );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setRepositoryEntries( repositoryEntries );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setRepositoryEntriesCount( repositoryEntriesCount );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setShowBreadcrumb( true );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setTabName( dlItemSelectorViewDisplayContext.getTitle(locale) );
      _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.setUploadURL( dlItemSelectorViewDisplayContext.getUploadURL(request, liferayPortletResponse) );
      int _jspx_eval_liferay$1item$1selector_repository$1entry$1browser_0 = _jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.doStartTag();
      if (_jspx_th_liferay$1item$1selector_repository$1entry$1browser_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1item$1selector_repository$1entry$1browser_uploadURL_tabName_showBreadcrumb_repositoryEntriesCount_repositoryEntries_portletURL_maxFileSize_itemSelectorReturnTypeResolver_itemSelectedEventName_extensions_emptyResultsMessage_dlMimeTypeDisplayContext_nobody.reuse(_jspx_th_liferay$1item$1selector_repository$1entry$1browser_0);
        return;
      }
      _jspx_tagPool_liferay$1item$1selector_repository$1entry$1browser_uploadURL_tabName_showBreadcrumb_repositoryEntriesCount_repositoryEntries_portletURL_maxFileSize_itemSelectorReturnTypeResolver_itemSelectedEventName_extensions_emptyResultsMessage_dlMimeTypeDisplayContext_nobody.reuse(_jspx_th_liferay$1item$1selector_repository$1entry$1browser_0);
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
