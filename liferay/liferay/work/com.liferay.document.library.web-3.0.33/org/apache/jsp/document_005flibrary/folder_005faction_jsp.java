package org.apache.jsp.document_005flibrary;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.util.AssetHelper;
import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.display.context.DLEditFileEntryDisplayContext;
import com.liferay.document.library.display.context.DLFilePicker;
import com.liferay.document.library.display.context.DLViewFileEntryHistoryDisplayContext;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.document.conversion.DocumentConversionUtil;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryTypeException;
import com.liferay.document.library.kernel.exception.DuplicateFolderNameException;
import com.liferay.document.library.kernel.exception.DuplicateRepositoryNameException;
import com.liferay.document.library.kernel.exception.FileEntryLockException;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileMimeTypeException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileShortcutPermissionException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.FolderNameException;
import com.liferay.document.library.kernel.exception.InvalidFileVersionException;
import com.liferay.document.library.kernel.exception.InvalidFolderException;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.exception.NoSuchMetadataSetException;
import com.liferay.document.library.kernel.exception.RepositoryNameException;
import com.liferay.document.library.kernel.exception.RequiredFileEntryTypeException;
import com.liferay.document.library.kernel.exception.RequiredFileException;
import com.liferay.document.library.kernel.exception.SourceFileNameException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeServiceUtil;
import com.liferay.document.library.kernel.util.AudioProcessorUtil;
import com.liferay.document.library.kernel.util.DLProcessorRegistryUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.ImageProcessorUtil;
import com.liferay.document.library.kernel.util.PDFProcessorUtil;
import com.liferay.document.library.kernel.util.RawMetadataProcessor;
import com.liferay.document.library.kernel.util.VideoProcessorUtil;
import com.liferay.document.library.web.internal.constants.DLWebKeys;
import com.liferay.document.library.web.internal.dao.search.DLResultRowSplitter;
import com.liferay.document.library.web.internal.dao.search.IGResultRowSplitter;
import com.liferay.document.library.web.internal.display.context.DLAdminDisplayContext;
import com.liferay.document.library.web.internal.display.context.DLAdminManagementToolbarDisplayContext;
import com.liferay.document.library.web.internal.display.context.DLAdminNavigationDisplayContext;
import com.liferay.document.library.web.internal.display.context.DLDisplayContextProvider;
import com.liferay.document.library.web.internal.display.context.DLSelectRestrictedFileEntryTypesDisplayContext;
import com.liferay.document.library.web.internal.display.context.DLViewFileEntryTypesDisplayContext;
import com.liferay.document.library.web.internal.display.context.IGDisplayContextProvider;
import com.liferay.document.library.web.internal.display.context.logic.DLPortletInstanceSettingsHelper;
import com.liferay.document.library.web.internal.display.context.logic.DLVisualizationHelper;
import com.liferay.document.library.web.internal.display.context.logic.UIItemsBuilder;
import com.liferay.document.library.web.internal.display.context.util.DLRequestHelper;
import com.liferay.document.library.web.internal.display.context.util.IGRequestHelper;
import com.liferay.document.library.web.internal.dynamic.data.mapping.util.DLDDMDisplay;
import com.liferay.document.library.web.internal.portlet.action.ActionUtil;
import com.liferay.document.library.web.internal.portlet.action.EditFileEntryMVCActionCommand;
import com.liferay.document.library.web.internal.search.EntriesChecker;
import com.liferay.document.library.web.internal.search.EntriesMover;
import com.liferay.document.library.web.internal.security.permission.resource.DLFileEntryPermission;
import com.liferay.document.library.web.internal.security.permission.resource.DLFileEntryTypePermission;
import com.liferay.document.library.web.internal.security.permission.resource.DLFileShortcutPermission;
import com.liferay.document.library.web.internal.security.permission.resource.DLFolderPermission;
import com.liferay.document.library.web.internal.security.permission.resource.DLPermission;
import com.liferay.document.library.web.internal.settings.DLPortletInstanceSettings;
import com.liferay.document.library.web.internal.util.DLBreadcrumbUtil;
import com.liferay.document.library.web.internal.util.DLSubscriptionUtil;
import com.liferay.document.library.web.internal.util.DLTrashUtil;
import com.liferay.document.library.web.internal.util.DLWebComponentProvider;
import com.liferay.document.library.web.internal.util.IGUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.DDMStructureManager;
import com.liferay.dynamic.data.mapping.kernel.DDMStructureManagerUtil;
import com.liferay.dynamic.data.mapping.kernel.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.kernel.StorageFieldRequiredException;
import com.liferay.dynamic.data.mapping.kernel.StructureDefinitionException;
import com.liferay.dynamic.data.mapping.kernel.StructureDuplicateElementException;
import com.liferay.dynamic.data.mapping.kernel.StructureNameException;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.dynamic.data.mapping.util.DDMNavigationHelper;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPDropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.image.gallery.display.kernel.display.context.IGViewFileVersionDisplayContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.InvalidRepositoryException;
import com.liferay.portal.kernel.exception.NoSuchRepositoryException;
import com.liferay.portal.kernel.exception.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.lock.DuplicateLockException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.repository.AuthenticationRepositoryException;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.RepositoryConfiguration;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.RepositoryProviderUtil;
import com.liferay.portal.kernel.repository.capabilities.CommentCapability;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFileEntriesCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.JavaScriptMenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.repository.registry.RepositoryClassDefinition;
import com.liferay.portal.repository.registry.RepositoryClassDefinitionCatalogUtil;
import com.liferay.portal.upload.LiferayFileItem;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.DLGroupServiceSettings;
import com.liferay.portlet.documentlibrary.constants.DLConstants;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.portlet.usersadmin.search.GroupSearchTerms;
import com.liferay.taglib.search.ResultRow;
import com.liferay.taglib.util.PortalIncludeUtil;
import com.liferay.trash.model.TrashEntry;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import com.liferay.document.library.configuration.DLConfiguration;
import com.liferay.document.library.web.internal.util.RepositoryClassDefinitionUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.util.RepositoryUtil;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

public final class folder_005faction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/document_library/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/document_library/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_renderURL_windowState_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_var_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_include_servletContext_page_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_renderURL_windowState_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_var_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.release();
    _jspx_tagPool_portlet_renderURL_var.release();
    _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_renderURL_windowState_var.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_portlet_resourceURL_var_id.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.release();
    _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.release();
    _jspx_tagPool_c_when_test.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

DLTrashUtil dlTrashUtil = (DLTrashUtil)request.getAttribute(DLWebKeys.DOCUMENT_LIBRARY_TRASH_UTIL);

DLWebComponentProvider dlWebComponentProvider = DLWebComponentProvider.getDLWebComponentProvider();

DLDisplayContextProvider dlDisplayContextProvider = dlWebComponentProvider.getDLDisplayContextProvider();
IGDisplayContextProvider igDisplayContextProvider = dlWebComponentProvider.getIGDisplayContextProvider();

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n");

DLRequestHelper dlRequestHelper = new DLRequestHelper(request);

String portletId = dlRequestHelper.getResourcePortletId();

portletName = dlRequestHelper.getResourcePortletName();

String portletResource = dlRequestHelper.getPortletResource();

DLAdminDisplayContext dlAdminDisplayContext = (DLAdminDisplayContext)request.getAttribute(DLWebKeys.DOCUMENT_LIBRARY_ADMIN_DISPLAY_CONTEXT);

if (dlAdminDisplayContext == null) {
	dlAdminDisplayContext = new DLAdminDisplayContext(liferayPortletRequest, liferayPortletResponse);

	request.setAttribute(DLWebKeys.DOCUMENT_LIBRARY_ADMIN_DISPLAY_CONTEXT, dlAdminDisplayContext);
}

DLConfiguration dlConfiguration = ConfigurationProviderUtil.getSystemConfiguration(DLConfiguration.class);
DLGroupServiceSettings dlGroupServiceSettings = dlRequestHelper.getDLGroupServiceSettings();
DLPortletInstanceSettings dlPortletInstanceSettings = dlRequestHelper.getDLPortletInstanceSettings();

long rootFolderId = dlAdminDisplayContext.getRootFolderId();
String rootFolderName = dlAdminDisplayContext.getRootFolderName();

boolean showComments = ParamUtil.getBoolean(request, "showComments", true);
boolean showHeader = ParamUtil.getBoolean(request, "showHeader", true);

StagingGroupHelper stagingGroupHelper = StagingGroupHelperUtil.getStagingGroupHelper();

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String randomNamespace = null;

if (portletName.equals(DLPortletKeys.DOCUMENT_LIBRARY) || portletName.equals(DLPortletKeys.DOCUMENT_LIBRARY_ADMIN)) {
	randomNamespace = PortalUtil.generateRandomKey(request, "portlet_document_library_folder_action") + StringPool.UNDERLINE;
}
else {
	randomNamespace = PortalUtil.generateRandomKey(request, "portlet_image_gallery_display_folder_action") + StringPool.UNDERLINE;
}

String redirect = currentURL;

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Folder folder = null;

long repositoryId = 0;

if (row != null) {
	Object result = row.getObject();

	if (result instanceof Folder) {
		folder = (Folder)result;

		repositoryId = folder.getRepositoryId();
	}
}
else {
	folder = (Folder)request.getAttribute("info_panel.jsp-folder");

	repositoryId = GetterUtil.getLong((String)request.getAttribute("view.jsp-repositoryId"));
}

long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

if (folder != null) {
	folderId = folder.getFolderId();
}

int status = WorkflowConstants.STATUS_APPROVED;

if (permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId)) {
	status = WorkflowConstants.STATUS_ANY;
}

boolean folderSelected = GetterUtil.getBoolean((String)request.getAttribute("view_entries.jsp-folderSelected"));

String modelResource = null;
String modelResourceDescription = null;
String resourcePrimKey = null;

boolean showPermissionsURL = false;

if (folder != null) {
	modelResource = DLFolderConstants.getClassName();
	modelResourceDescription = folder.getName();
	resourcePrimKey = String.valueOf(folderId);

	showPermissionsURL = DLFolderPermission.contains(permissionChecker, folder, ActionKeys.PERMISSIONS);
}
else {
	modelResource = "com.liferay.document.library";
	modelResourceDescription = themeDisplay.getScopeGroupName();
	resourcePrimKey = String.valueOf(scopeGroupId);

	showPermissionsURL = DLPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
}

DLPortletInstanceSettingsHelper dlPortletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(dlRequestHelper);

boolean view = false;

if ((row == null) && portletName.equals(DLPortletKeys.MEDIA_GALLERY_DISPLAY)) {
	view = true;
}

      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( dlPortletInstanceSettingsHelper.isShowActions() );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  liferay-ui:icon-menu
        com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_0 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
        _jspx_th_liferay$1ui_icon$1menu_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_icon$1menu_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_liferay$1ui_icon$1menu_0.setDirection("left-side");
        _jspx_th_liferay$1ui_icon$1menu_0.setIcon( StringPool.BLANK );
        _jspx_th_liferay$1ui_icon$1menu_0.setMarkupView("lexicon");
        _jspx_th_liferay$1ui_icon$1menu_0.setMessage( StringPool.BLANK );
        _jspx_th_liferay$1ui_icon$1menu_0.setShowWhenSingleIcon( true );
        int _jspx_eval_liferay$1ui_icon$1menu_0 = _jspx_th_liferay$1ui_icon$1menu_0.doStartTag();
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_icon$1menu_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_icon$1menu_0.doInitBody();
          }
          do {
            out.write("\n\n\t\t");

		boolean hasViewPermission = DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.VIEW);
		
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_1.setPageContext(_jspx_page_context);
            _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            _jspx_th_c_if_1.setTest( hasViewPermission && ((folder == null) || !folder.isMountPoint()) );
            int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
            if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:resourceURL
              com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_var_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
              _jspx_th_portlet_resourceURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              _jspx_th_portlet_resourceURL_0.setId("/document_library/download_folder");
              _jspx_th_portlet_resourceURL_0.setVar("downloadURL");
              int _jspx_eval_portlet_resourceURL_0 = _jspx_th_portlet_resourceURL_0.doStartTag();
              if (_jspx_eval_portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
                _jspx_th_portlet_param_0.setName("folderId");
                _jspx_th_portlet_param_0.setValue( String.valueOf(folderId) );
                int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
                if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
                _jspx_th_portlet_param_1.setName("repositoryId");
                _jspx_th_portlet_param_1.setValue( String.valueOf(repositoryId) );
                int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_0);
                return;
              }
              _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_0);
              java.lang.String downloadURL = null;
              downloadURL = (java.lang.String) _jspx_page_context.findAttribute("downloadURL");
              out.write("\n\n\t\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
              _jspx_th_liferay$1ui_icon_0.setMessage("download");
              _jspx_th_liferay$1ui_icon_0.setMethod("get");
              _jspx_th_liferay$1ui_icon_0.setUrl( downloadURL );
              int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
              if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
            out.write("\n\n\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
            if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_0.setPageContext(_jspx_page_context);
              _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              _jspx_th_c_when_0.setTest( folder != null );
              int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
              if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t");

				boolean hasUpdatePermission = DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.UPDATE);
				
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_c_if_2.setTest( hasUpdatePermission );
                int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  c:choose
                  com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                  _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                  int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                  if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  c:when
                    com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                    _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    _jspx_th_c_when_1.setTest( !folder.isMountPoint() );
                    int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                    if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  portlet:renderURL
                      com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                      _jspx_th_portlet_renderURL_0.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_renderURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_portlet_renderURL_0.setVar("editURL");
                      int _jspx_eval_portlet_renderURL_0 = _jspx_th_portlet_renderURL_0.doStartTag();
                      if (_jspx_eval_portlet_renderURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_portlet_param_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_0, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                        _jspx_th_portlet_param_3.setName("redirect");
                        _jspx_th_portlet_param_3.setValue( redirect );
                        int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                        if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                        _jspx_th_portlet_param_4.setName("folderId");
                        _jspx_th_portlet_param_4.setValue( String.valueOf(folderId) );
                        int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
                        if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
                        _jspx_th_portlet_param_5.setName("repositoryId");
                        _jspx_th_portlet_param_5.setValue( String.valueOf(repositoryId) );
                        int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
                        if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
                        out.write("\n\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                        return;
                      }
                      _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_0);
                      java.lang.String editURL = null;
                      editURL = (java.lang.String) _jspx_page_context.findAttribute("editURL");
                      out.write("\n\n\t\t\t\t\t\t\t");
                      //  liferay-ui:icon
                      com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                      _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                      _jspx_th_liferay$1ui_icon_1.setMessage("edit");
                      _jspx_th_liferay$1ui_icon_1.setUrl( editURL );
                      int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
                      if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                      return;
                    }
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    out.write("\n\t\t\t\t\t\t");
                    //  c:otherwise
                    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t");
                      //  portlet:renderURL
                      com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_1 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                      _jspx_th_portlet_renderURL_1.setPageContext(_jspx_page_context);
                      _jspx_th_portlet_renderURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_portlet_renderURL_1.setVar("editURL");
                      int _jspx_eval_portlet_renderURL_1 = _jspx_th_portlet_renderURL_1.doStartTag();
                      if (_jspx_eval_portlet_renderURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_portlet_param_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_1, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_7 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_7.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                        _jspx_th_portlet_param_7.setName("redirect");
                        _jspx_th_portlet_param_7.setValue( redirect );
                        int _jspx_eval_portlet_param_7 = _jspx_th_portlet_param_7.doStartTag();
                        if (_jspx_th_portlet_param_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_7);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_8 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_8.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                        _jspx_th_portlet_param_8.setName("folderId");
                        _jspx_th_portlet_param_8.setValue( String.valueOf(folderId) );
                        int _jspx_eval_portlet_param_8 = _jspx_th_portlet_param_8.doStartTag();
                        if (_jspx_th_portlet_param_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_8);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  portlet:param
                        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_9 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                        _jspx_th_portlet_param_9.setPageContext(_jspx_page_context);
                        _jspx_th_portlet_param_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
                        _jspx_th_portlet_param_9.setName("repositoryId");
                        _jspx_th_portlet_param_9.setValue( String.valueOf(repositoryId) );
                        int _jspx_eval_portlet_param_9 = _jspx_th_portlet_param_9.doStartTag();
                        if (_jspx_th_portlet_param_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                          return;
                        }
                        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_9);
                        out.write("\n\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_portlet_renderURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                        return;
                      }
                      _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_1);
                      java.lang.String editURL = null;
                      editURL = (java.lang.String) _jspx_page_context.findAttribute("editURL");
                      out.write("\n\n\t\t\t\t\t\t\t");
                      //  liferay-ui:icon
                      com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                      _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                      _jspx_th_liferay$1ui_icon_2.setMessage("edit");
                      _jspx_th_liferay$1ui_icon_2.setUrl( editURL );
                      int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
                      if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                      out.write("\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                      return;
                    }
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                    return;
                  }
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_c_if_3.setTest( hasUpdatePermission && !folder.isMountPoint() );
                int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_2 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_2.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                  _jspx_th_portlet_renderURL_2.setVar("moveURL");
                  int _jspx_eval_portlet_renderURL_2 = _jspx_th_portlet_renderURL_2.doStartTag();
                  if (_jspx_eval_portlet_renderURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_2, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_11 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_11.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                    _jspx_th_portlet_param_11.setName("redirect");
                    _jspx_th_portlet_param_11.setValue( redirect );
                    int _jspx_eval_portlet_param_11 = _jspx_th_portlet_param_11.doStartTag();
                    if (_jspx_th_portlet_param_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_11);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_12 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_12.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                    _jspx_th_portlet_param_12.setName("repositoryId");
                    _jspx_th_portlet_param_12.setValue( String.valueOf(repositoryId) );
                    int _jspx_eval_portlet_param_12 = _jspx_th_portlet_param_12.doStartTag();
                    if (_jspx_th_portlet_param_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_12);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_13 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_13.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
                    _jspx_th_portlet_param_13.setName("rowIdsFolder");
                    _jspx_th_portlet_param_13.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_13 = _jspx_th_portlet_param_13.doStartTag();
                    if (_jspx_th_portlet_param_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_13);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_2);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_2);
                  java.lang.String moveURL = null;
                  moveURL = (java.lang.String) _jspx_page_context.findAttribute("moveURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_3 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_3.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                  _jspx_th_liferay$1ui_icon_3.setMessage("move");
                  _jspx_th_liferay$1ui_icon_3.setUrl( moveURL );
                  int _jspx_eval_liferay$1ui_icon_3 = _jspx_th_liferay$1ui_icon_3.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_3);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_c_if_4.setTest( DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) && !folder.isMountPoint() );
                int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_3 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_3.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                  _jspx_th_portlet_renderURL_3.setVar("addFolderURL");
                  int _jspx_eval_portlet_renderURL_3 = _jspx_th_portlet_renderURL_3.doStartTag();
                  if (_jspx_eval_portlet_renderURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_3, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_15 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_15.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_15.setName("redirect");
                    _jspx_th_portlet_param_15.setValue( currentURL );
                    int _jspx_eval_portlet_param_15 = _jspx_th_portlet_param_15.doStartTag();
                    if (_jspx_th_portlet_param_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_15);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_16 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_16.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_16.setName("repositoryId");
                    _jspx_th_portlet_param_16.setValue( String.valueOf(repositoryId) );
                    int _jspx_eval_portlet_param_16 = _jspx_th_portlet_param_16.doStartTag();
                    if (_jspx_th_portlet_param_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_16);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_17 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_17.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_17.setName("parentFolderId");
                    _jspx_th_portlet_param_17.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_17 = _jspx_th_portlet_param_17.doStartTag();
                    if (_jspx_th_portlet_param_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_17);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_18 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_18.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
                    _jspx_th_portlet_param_18.setName("ignoreRootFolder");
                    _jspx_th_portlet_param_18.setValue( Boolean.TRUE.toString() );
                    int _jspx_eval_portlet_param_18 = _jspx_th_portlet_param_18.doStartTag();
                    if (_jspx_th_portlet_param_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_18);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_3);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_3);
                  java.lang.String addFolderURL = null;
                  addFolderURL = (java.lang.String) _jspx_page_context.findAttribute("addFolderURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_4 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_4.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
                  _jspx_th_liferay$1ui_icon_4.setMessage("add-subfolder");
                  _jspx_th_liferay$1ui_icon_4.setUrl( addFolderURL );
                  int _jspx_eval_liferay$1ui_icon_4 = _jspx_th_liferay$1ui_icon_4.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_4);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_c_if_5.setTest( folder.isMountPoint() );
                int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\n\t\t\t\t\t");

					LocalRepository localRepository = null;

					try {
						localRepository = RepositoryProviderUtil.getLocalRepository(folder.getRepositoryId());
					}
					catch (RepositoryException re) {
					}

					if ((localRepository != null) && localRepository.isCapabilityProvided(TemporaryFileEntriesCapability.class)) {
					
                  out.write("\n\n\t\t\t\t\t\t");
                  //  portlet:actionURL
                  com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                  _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                  _jspx_th_portlet_actionURL_0.setName("/document_library/edit_folder");
                  _jspx_th_portlet_actionURL_0.setVar("deleteExpiredTemporaryFileEntriesURL");
                  int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
                  if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_19 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_19.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                    _jspx_th_portlet_param_19.setName( Constants.CMD );
                    _jspx_th_portlet_param_19.setValue("deleteExpiredTemporaryFileEntries");
                    int _jspx_eval_portlet_param_19 = _jspx_th_portlet_param_19.doStartTag();
                    if (_jspx_th_portlet_param_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_19);
                    out.write("\n\t\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_20 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_20.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                    _jspx_th_portlet_param_20.setName("redirect");
                    _jspx_th_portlet_param_20.setValue( currentURL );
                    int _jspx_eval_portlet_param_20 = _jspx_th_portlet_param_20.doStartTag();
                    if (_jspx_th_portlet_param_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_20);
                    out.write("\n\t\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_21 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_21.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                    _jspx_th_portlet_param_21.setName("repositoryId");
                    _jspx_th_portlet_param_21.setValue( String.valueOf(folder.getRepositoryId()) );
                    int _jspx_eval_portlet_param_21 = _jspx_th_portlet_param_21.doStartTag();
                    if (_jspx_th_portlet_param_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_21);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                    return;
                  }
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                  java.lang.String deleteExpiredTemporaryFileEntriesURL = null;
                  deleteExpiredTemporaryFileEntriesURL = (java.lang.String) _jspx_page_context.findAttribute("deleteExpiredTemporaryFileEntriesURL");
                  out.write("\n\n\t\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_5 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_5.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                  _jspx_th_liferay$1ui_icon_5.setMessage("delete-expired-temporary-files");
                  _jspx_th_liferay$1ui_icon_5.setUrl( deleteExpiredTemporaryFileEntriesURL );
                  int _jspx_eval_liferay$1ui_icon_5 = _jspx_th_liferay$1ui_icon_5.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_5);
                  out.write("\n\n\t\t\t\t\t");

					}
					
                  out.write("\n\n\t\t\t\t");
                }
                if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              out.write("\n\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
              if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t");

				boolean workflowEnabled = WorkflowEngineManagerUtil.isDeployed() && (WorkflowHandlerRegistryUtil.getWorkflowHandler(DLFileEntry.class.getName()) != null);
				
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                _jspx_th_c_if_6.setTest( workflowEnabled && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.UPDATE) );
                int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_4 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_4.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                  _jspx_th_portlet_renderURL_4.setVar("editURL");
                  int _jspx_eval_portlet_renderURL_4 = _jspx_th_portlet_renderURL_4.doStartTag();
                  if (_jspx_eval_portlet_renderURL_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_4, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_23 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_23.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
                    _jspx_th_portlet_param_23.setName("redirect");
                    _jspx_th_portlet_param_23.setValue( redirect );
                    int _jspx_eval_portlet_param_23 = _jspx_th_portlet_param_23.doStartTag();
                    if (_jspx_th_portlet_param_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_23);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_24 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_24.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
                    _jspx_th_portlet_param_24.setName("folderId");
                    _jspx_th_portlet_param_24.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_24 = _jspx_th_portlet_param_24.doStartTag();
                    if (_jspx_th_portlet_param_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_24);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_25 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_25.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
                    _jspx_th_portlet_param_25.setName("repositoryId");
                    _jspx_th_portlet_param_25.setValue( String.valueOf(repositoryId) );
                    int _jspx_eval_portlet_param_25 = _jspx_th_portlet_param_25.doStartTag();
                    if (_jspx_th_portlet_param_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_25);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_26 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_26.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
                    _jspx_th_portlet_param_26.setName("rootFolder");
                    _jspx_th_portlet_param_26.setValue( Boolean.TRUE.toString() );
                    int _jspx_eval_portlet_param_26 = _jspx_th_portlet_param_26.doStartTag();
                    if (_jspx_th_portlet_param_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_26);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_4);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_4);
                  java.lang.String editURL = null;
                  editURL = (java.lang.String) _jspx_page_context.findAttribute("editURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_6 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_6.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                  _jspx_th_liferay$1ui_icon_6.setMessage("edit");
                  _jspx_th_liferay$1ui_icon_6.setUrl( editURL );
                  int _jspx_eval_liferay$1ui_icon_6 = _jspx_th_liferay$1ui_icon_6.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_6);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                _jspx_th_c_if_7.setTest( DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) );
                int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_5 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_5.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                  _jspx_th_portlet_renderURL_5.setVar("addFolderURL");
                  int _jspx_eval_portlet_renderURL_5 = _jspx_th_portlet_renderURL_5.doStartTag();
                  if (_jspx_eval_portlet_renderURL_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_5, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_28 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_28.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
                    _jspx_th_portlet_param_28.setName("redirect");
                    _jspx_th_portlet_param_28.setValue( currentURL );
                    int _jspx_eval_portlet_param_28 = _jspx_th_portlet_param_28.doStartTag();
                    if (_jspx_th_portlet_param_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_28);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_29 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_29.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
                    _jspx_th_portlet_param_29.setName("repositoryId");
                    _jspx_th_portlet_param_29.setValue( String.valueOf(repositoryId) );
                    int _jspx_eval_portlet_param_29 = _jspx_th_portlet_param_29.doStartTag();
                    if (_jspx_th_portlet_param_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_29);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_30 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_30.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
                    _jspx_th_portlet_param_30.setName("parentFolderId");
                    _jspx_th_portlet_param_30.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_30 = _jspx_th_portlet_param_30.doStartTag();
                    if (_jspx_th_portlet_param_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_30);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_31 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_31.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
                    _jspx_th_portlet_param_31.setName("ignoreRootFolder");
                    _jspx_th_portlet_param_31.setValue( Boolean.TRUE.toString() );
                    int _jspx_eval_portlet_param_31 = _jspx_th_portlet_param_31.doStartTag();
                    if (_jspx_th_portlet_param_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_31);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_5);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_5);
                  java.lang.String addFolderURL = null;
                  addFolderURL = (java.lang.String) _jspx_page_context.findAttribute("addFolderURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_7 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_7.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                  _jspx_th_liferay$1ui_icon_7.setMessage("add-folder");
                  _jspx_th_liferay$1ui_icon_7.setUrl( addFolderURL );
                  int _jspx_eval_liferay$1ui_icon_7 = _jspx_th_liferay$1ui_icon_7.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_7);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_7);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                _jspx_th_c_if_8.setTest( DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_REPOSITORY) );
                int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_6 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_6.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  _jspx_th_portlet_renderURL_6.setVar("addRepositoryURL");
                  int _jspx_eval_portlet_renderURL_6 = _jspx_th_portlet_renderURL_6.doStartTag();
                  if (_jspx_eval_portlet_renderURL_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_6, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_33 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_33.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_6);
                    _jspx_th_portlet_param_33.setName("redirect");
                    _jspx_th_portlet_param_33.setValue( currentURL );
                    int _jspx_eval_portlet_param_33 = _jspx_th_portlet_param_33.doStartTag();
                    if (_jspx_th_portlet_param_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_33);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_34 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_34.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_6);
                    _jspx_th_portlet_param_34.setName("folderId");
                    _jspx_th_portlet_param_34.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_34 = _jspx_th_portlet_param_34.doStartTag();
                    if (_jspx_th_portlet_param_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_34);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_6);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_6);
                  java.lang.String addRepositoryURL = null;
                  addRepositoryURL = (java.lang.String) _jspx_page_context.findAttribute("addRepositoryURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_8 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_8.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                  _jspx_th_liferay$1ui_icon_8.setMessage("add-repository");
                  _jspx_th_liferay$1ui_icon_8.setUrl( addRepositoryURL );
                  int _jspx_eval_liferay$1ui_icon_8 = _jspx_th_liferay$1ui_icon_8.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_8);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_8);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_9.setPageContext(_jspx_page_context);
            _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            _jspx_th_c_if_9.setTest( portletName.equals(DLPortletKeys.MEDIA_GALLERY_DISPLAY) );
            int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
            if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_10.setPageContext(_jspx_page_context);
              _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
              _jspx_th_c_if_10.setTest( ((folder == null) || !folder.isMountPoint()) && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) );
              int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
              if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_7 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_portlet_renderURL_7.setPageContext(_jspx_page_context);
                _jspx_th_portlet_renderURL_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
                _jspx_th_portlet_renderURL_7.setVar("editFileEntryURL");
                int _jspx_eval_portlet_renderURL_7 = _jspx_th_portlet_renderURL_7.doStartTag();
                if (_jspx_eval_portlet_renderURL_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_portlet_param_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_7, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_36 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_36.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_7);
                  _jspx_th_portlet_param_36.setName("redirect");
                  _jspx_th_portlet_param_36.setValue( currentURL );
                  int _jspx_eval_portlet_param_36 = _jspx_th_portlet_param_36.doStartTag();
                  if (_jspx_th_portlet_param_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_36);
                  out.write("\n\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_37 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_37.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_7);
                  _jspx_th_portlet_param_37.setName("repositoryId");
                  _jspx_th_portlet_param_37.setValue( String.valueOf(repositoryId) );
                  int _jspx_eval_portlet_param_37 = _jspx_th_portlet_param_37.doStartTag();
                  if (_jspx_th_portlet_param_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_37);
                  out.write("\n\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_38 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_38.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_7);
                  _jspx_th_portlet_param_38.setName("folderId");
                  _jspx_th_portlet_param_38.setValue( String.valueOf(folderId) );
                  int _jspx_eval_portlet_param_38 = _jspx_th_portlet_param_38.doStartTag();
                  if (_jspx_th_portlet_param_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_38);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_portlet_renderURL_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_7);
                  return;
                }
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_7);
                java.lang.String editFileEntryURL = null;
                editFileEntryURL = (java.lang.String) _jspx_page_context.findAttribute("editFileEntryURL");
                out.write("\n\n\t\t\t\t");
                //  liferay-ui:icon
                com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_9 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                _jspx_th_liferay$1ui_icon_9.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
                _jspx_th_liferay$1ui_icon_9.setMessage("add-file-entry");
                _jspx_th_liferay$1ui_icon_9.setUrl( editFileEntryURL );
                int _jspx_eval_liferay$1ui_icon_9 = _jspx_th_liferay$1ui_icon_9.doStartTag();
                if (_jspx_th_liferay$1ui_icon_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_9);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_9);
                out.write("\n\n\t\t\t\t");
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_11.setPageContext(_jspx_page_context);
                _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
                _jspx_th_c_if_11.setTest( (folder == null) || folder.isSupportsMultipleUpload() );
                int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
                if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_8 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_8.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
                  _jspx_th_portlet_renderURL_8.setVar("addMultipleFileEntriesURL");
                  int _jspx_eval_portlet_renderURL_8 = _jspx_th_portlet_renderURL_8.doStartTag();
                  if (_jspx_eval_portlet_renderURL_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    if (_jspx_meth_portlet_param_39((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_8, _jspx_page_context))
                      return;
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_40 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_40.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_8);
                    _jspx_th_portlet_param_40.setName("redirect");
                    _jspx_th_portlet_param_40.setValue( currentURL );
                    int _jspx_eval_portlet_param_40 = _jspx_th_portlet_param_40.doStartTag();
                    if (_jspx_th_portlet_param_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_40);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_41 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_41.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_8);
                    _jspx_th_portlet_param_41.setName("backURL");
                    _jspx_th_portlet_param_41.setValue( currentURL );
                    int _jspx_eval_portlet_param_41 = _jspx_th_portlet_param_41.doStartTag();
                    if (_jspx_th_portlet_param_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_41);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_42 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_42.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_8);
                    _jspx_th_portlet_param_42.setName("repositoryId");
                    _jspx_th_portlet_param_42.setValue( String.valueOf(repositoryId) );
                    int _jspx_eval_portlet_param_42 = _jspx_th_portlet_param_42.doStartTag();
                    if (_jspx_th_portlet_param_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_42);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_43 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_43.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_8);
                    _jspx_th_portlet_param_43.setName("folderId");
                    _jspx_th_portlet_param_43.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_43 = _jspx_th_portlet_param_43.doStartTag();
                    if (_jspx_th_portlet_param_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_43);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_43);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_8);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_8);
                  java.lang.String addMultipleFileEntriesURL = null;
                  addMultipleFileEntriesURL = (java.lang.String) _jspx_page_context.findAttribute("addMultipleFileEntriesURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon
                  com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_10 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                  _jspx_th_liferay$1ui_icon_10.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_11);
                  _jspx_th_liferay$1ui_icon_10.setCssClass("hide upload-multiple-documents");
                  _jspx_th_liferay$1ui_icon_10.setMessage( portletName.equals(DLPortletKeys.MEDIA_GALLERY_DISPLAY) ? "multiple-media" : "multiple-documents" );
                  _jspx_th_liferay$1ui_icon_10.setUrl( addMultipleFileEntriesURL );
                  int _jspx_eval_liferay$1ui_icon_10 = _jspx_th_liferay$1ui_icon_10.doStartTag();
                  if (_jspx_th_liferay$1ui_icon_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_10);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_10);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
              out.write("\n\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_12 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_12.setPageContext(_jspx_page_context);
              _jspx_th_c_if_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
              _jspx_th_c_if_12.setTest( hasViewPermission && (DLAppServiceUtil.getFileEntriesAndFileShortcutsCount(repositoryId, folderId, status) > 0) );
              int _jspx_eval_c_if_12 = _jspx_th_c_if_12.doStartTag();
              if (_jspx_eval_c_if_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  liferay-ui:icon
                com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_11 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.get(com.liferay.taglib.ui.IconTag.class);
                _jspx_th_liferay$1ui_icon_11.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_12);
                _jspx_th_liferay$1ui_icon_11.setCssClass( randomNamespace + "-slide-show" );
                _jspx_th_liferay$1ui_icon_11.setMessage("view-slide-show");
                _jspx_th_liferay$1ui_icon_11.setUrl("javascript:;");
                int _jspx_eval_liferay$1ui_icon_11 = _jspx_th_liferay$1ui_icon_11.doStartTag();
                if (_jspx_th_liferay$1ui_icon_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_11);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon_url_message_cssClass_nobody.reuse(_jspx_th_liferay$1ui_icon_11);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_if_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_12);
              out.write("\n\n\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_13 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_13.setPageContext(_jspx_page_context);
              _jspx_th_c_if_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
              _jspx_th_c_if_13.setTest( ((folder == null) || (!folder.isMountPoint() && folder.isSupportsShortcuts())) && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_SHORTCUT) );
              int _jspx_eval_c_if_13 = _jspx_th_c_if_13.doStartTag();
              if (_jspx_eval_c_if_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  portlet:renderURL
                com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_9 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                _jspx_th_portlet_renderURL_9.setPageContext(_jspx_page_context);
                _jspx_th_portlet_renderURL_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                _jspx_th_portlet_renderURL_9.setVar("editFileShortcutURL");
                int _jspx_eval_portlet_renderURL_9 = _jspx_th_portlet_renderURL_9.doStartTag();
                if (_jspx_eval_portlet_renderURL_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_portlet_param_44((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_9, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_45 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_45.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_9);
                  _jspx_th_portlet_param_45.setName("redirect");
                  _jspx_th_portlet_param_45.setValue( currentURL );
                  int _jspx_eval_portlet_param_45 = _jspx_th_portlet_param_45.doStartTag();
                  if (_jspx_th_portlet_param_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_45);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_45);
                  out.write("\n\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_46 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_46.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_9);
                  _jspx_th_portlet_param_46.setName("repositoryId");
                  _jspx_th_portlet_param_46.setValue( String.valueOf(repositoryId) );
                  int _jspx_eval_portlet_param_46 = _jspx_th_portlet_param_46.doStartTag();
                  if (_jspx_th_portlet_param_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_46);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_46);
                  out.write("\n\t\t\t\t\t");
                  //  portlet:param
                  com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_47 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                  _jspx_th_portlet_param_47.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_param_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_9);
                  _jspx_th_portlet_param_47.setName("folderId");
                  _jspx_th_portlet_param_47.setValue( String.valueOf(folderId) );
                  int _jspx_eval_portlet_param_47 = _jspx_th_portlet_param_47.doStartTag();
                  if (_jspx_th_portlet_param_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_47);
                    return;
                  }
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_47);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_portlet_renderURL_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_9);
                  return;
                }
                _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_9);
                java.lang.String editFileShortcutURL = null;
                editFileShortcutURL = (java.lang.String) _jspx_page_context.findAttribute("editFileShortcutURL");
                out.write("\n\n\t\t\t\t");
                //  liferay-ui:icon
                com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_12 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                _jspx_th_liferay$1ui_icon_12.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_icon_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_13);
                _jspx_th_liferay$1ui_icon_12.setMessage("add-shortcut");
                _jspx_th_liferay$1ui_icon_12.setUrl( editFileShortcutURL );
                int _jspx_eval_liferay$1ui_icon_12 = _jspx_th_liferay$1ui_icon_12.doStartTag();
                if (_jspx_th_liferay$1ui_icon_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_12);
                  return;
                }
                _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_12);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_if_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_13);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_14 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_14.setPageContext(_jspx_page_context);
            _jspx_th_c_if_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            _jspx_th_c_if_14.setTest( hasViewPermission && portletDisplay.isWebDAVEnabled() && ((folder == null) || (folder.getRepositoryId() == scopeGroupId)) );
            int _jspx_eval_c_if_14 = _jspx_th_c_if_14.doStartTag();
            if (_jspx_eval_c_if_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  liferay-util:include
              com.liferay.taglib.util.IncludeTag _jspx_th_liferay$1util_include_0 = (com.liferay.taglib.util.IncludeTag) _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.get(com.liferay.taglib.util.IncludeTag.class);
              _jspx_th_liferay$1util_include_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1util_include_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_14);
              _jspx_th_liferay$1util_include_0.setPage("/document_library/access_from_desktop.jsp");
              _jspx_th_liferay$1util_include_0.setServletContext( application );
              int _jspx_eval_liferay$1util_include_0 = _jspx_th_liferay$1util_include_0.doStartTag();
              if (_jspx_th_liferay$1util_include_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
                return;
              }
              _jspx_tagPool_liferay$1util_include_servletContext_page_nobody.reuse(_jspx_th_liferay$1util_include_0);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_14);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_15 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_15.setPageContext(_jspx_page_context);
            _jspx_th_c_if_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            _jspx_th_c_if_15.setTest( showPermissionsURL );
            int _jspx_eval_c_if_15 = _jspx_th_c_if_15.doStartTag();
            if (_jspx_eval_c_if_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  liferay-security:permissionsURL
              com.liferay.taglib.security.PermissionsURLTag _jspx_th_liferay$1security_permissionsURL_0 = (com.liferay.taglib.security.PermissionsURLTag) _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.get(com.liferay.taglib.security.PermissionsURLTag.class);
              _jspx_th_liferay$1security_permissionsURL_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1security_permissionsURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
              _jspx_th_liferay$1security_permissionsURL_0.setModelResource( modelResource );
              _jspx_th_liferay$1security_permissionsURL_0.setModelResourceDescription( HtmlUtil.escape(modelResourceDescription) );
              _jspx_th_liferay$1security_permissionsURL_0.setResourcePrimKey( resourcePrimKey );
              _jspx_th_liferay$1security_permissionsURL_0.setVar("permissionsURL");
              _jspx_th_liferay$1security_permissionsURL_0.setWindowState( LiferayWindowState.POP_UP.toString() );
              int _jspx_eval_liferay$1security_permissionsURL_0 = _jspx_th_liferay$1security_permissionsURL_0.doStartTag();
              if (_jspx_th_liferay$1security_permissionsURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
                return;
              }
              _jspx_tagPool_liferay$1security_permissionsURL_windowState_var_resourcePrimKey_modelResourceDescription_modelResource_nobody.reuse(_jspx_th_liferay$1security_permissionsURL_0);
              java.lang.String permissionsURL = null;
              permissionsURL = (java.lang.String) _jspx_page_context.findAttribute("permissionsURL");
              out.write("\n\n\t\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_13 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_13.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_15);
              _jspx_th_liferay$1ui_icon_13.setMessage("permissions");
              _jspx_th_liferay$1ui_icon_13.setMethod("get");
              _jspx_th_liferay$1ui_icon_13.setUrl( permissionsURL );
              _jspx_th_liferay$1ui_icon_13.setUseDialog( true );
              int _jspx_eval_liferay$1ui_icon_13 = _jspx_th_liferay$1ui_icon_13.doStartTag();
              if (_jspx_th_liferay$1ui_icon_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_13);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_useDialog_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_13);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_15);
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_16 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_16.setPageContext(_jspx_page_context);
            _jspx_th_c_if_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            _jspx_th_c_if_16.setTest( (folder != null) && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.DELETE) );
            int _jspx_eval_c_if_16 = _jspx_th_c_if_16.doStartTag();
            if (_jspx_eval_c_if_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t");

			String mvcRenderCommandName = "/image_gallery_display/view";

			if (!portletName.equals(DLPortletKeys.MEDIA_GALLERY_DISPLAY)) {
				mvcRenderCommandName = "/document_library/view";

				if (folder.getParentFolderId() != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
					mvcRenderCommandName = "/document_library/view_folder";
				}
			}
			
              out.write("\n\n\t\t\t");
              //  c:choose
              com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
              _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
              _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_16);
              int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
              if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  c:when
                com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                _jspx_th_c_when_2.setTest( !folder.isMountPoint() );
                int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_10 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_10.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  _jspx_th_portlet_renderURL_10.setVar("redirectURL");
                  int _jspx_eval_portlet_renderURL_10 = _jspx_th_portlet_renderURL_10.doStartTag();
                  if (_jspx_eval_portlet_renderURL_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_48 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_48.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_10);
                    _jspx_th_portlet_param_48.setName("mvcRenderCommandName");
                    _jspx_th_portlet_param_48.setValue( mvcRenderCommandName );
                    int _jspx_eval_portlet_param_48 = _jspx_th_portlet_param_48.doStartTag();
                    if (_jspx_th_portlet_param_48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_48);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_48);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_49 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_49.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_10);
                    _jspx_th_portlet_param_49.setName("folderId");
                    _jspx_th_portlet_param_49.setValue( String.valueOf(folder.getParentFolderId()) );
                    int _jspx_eval_portlet_param_49 = _jspx_th_portlet_param_49.doStartTag();
                    if (_jspx_th_portlet_param_49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_49);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_49);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_10);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_10);
                  java.lang.String redirectURL = null;
                  redirectURL = (java.lang.String) _jspx_page_context.findAttribute("redirectURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  portlet:actionURL
                  com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                  _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  _jspx_th_portlet_actionURL_1.setName("/document_library/edit_folder");
                  _jspx_th_portlet_actionURL_1.setVar("deleteURL");
                  int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
                  if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_50 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_50.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                    _jspx_th_portlet_param_50.setName( Constants.CMD );
                    _jspx_th_portlet_param_50.setValue( ((folder.getModel() instanceof DLFolder) && dlTrashUtil.isTrashEnabled(scopeGroupId, repositoryId)) ? Constants.MOVE_TO_TRASH : Constants.DELETE );
                    int _jspx_eval_portlet_param_50 = _jspx_th_portlet_param_50.doStartTag();
                    if (_jspx_th_portlet_param_50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_50);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_50);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_51 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_51.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                    _jspx_th_portlet_param_51.setName("redirect");
                    _jspx_th_portlet_param_51.setValue( (view || folderSelected) ? redirectURL : redirect );
                    int _jspx_eval_portlet_param_51 = _jspx_th_portlet_param_51.doStartTag();
                    if (_jspx_th_portlet_param_51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_51);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_51);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_52 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_52.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                    _jspx_th_portlet_param_52.setName("folderId");
                    _jspx_th_portlet_param_52.setValue( String.valueOf(folderId) );
                    int _jspx_eval_portlet_param_52 = _jspx_th_portlet_param_52.doStartTag();
                    if (_jspx_th_portlet_param_52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_52);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_52);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                    return;
                  }
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                  java.lang.String deleteURL = null;
                  deleteURL = (java.lang.String) _jspx_page_context.findAttribute("deleteURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon-delete
                  com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_0 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
                  _jspx_th_liferay$1ui_icon$1delete_0.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon$1delete_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                  _jspx_th_liferay$1ui_icon$1delete_0.setTrash( (folder.getModel() instanceof DLFolder) && dlTrashUtil.isTrashEnabled(scopeGroupId, repositoryId) );
                  _jspx_th_liferay$1ui_icon$1delete_0.setUrl( deleteURL );
                  int _jspx_eval_liferay$1ui_icon$1delete_0 = _jspx_th_liferay$1ui_icon$1delete_0.doStartTag();
                  if (_jspx_th_liferay$1ui_icon$1delete_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon$1delete_url_trash_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  return;
                }
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                out.write("\n\t\t\t\t");
                //  c:otherwise
                com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  portlet:renderURL
                  com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_11 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
                  _jspx_th_portlet_renderURL_11.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_renderURL_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_portlet_renderURL_11.setVar("redirectURL");
                  int _jspx_eval_portlet_renderURL_11 = _jspx_th_portlet_renderURL_11.doStartTag();
                  if (_jspx_eval_portlet_renderURL_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_53 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_53.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_11);
                    _jspx_th_portlet_param_53.setName("mvcRenderCommandName");
                    _jspx_th_portlet_param_53.setValue( mvcRenderCommandName );
                    int _jspx_eval_portlet_param_53 = _jspx_th_portlet_param_53.doStartTag();
                    if (_jspx_th_portlet_param_53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_53);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_53);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_54 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_54.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_54.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_11);
                    _jspx_th_portlet_param_54.setName("folderId");
                    _jspx_th_portlet_param_54.setValue( String.valueOf(folder.getParentFolderId()) );
                    int _jspx_eval_portlet_param_54 = _jspx_th_portlet_param_54.doStartTag();
                    if (_jspx_th_portlet_param_54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_54);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_54);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_renderURL_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_11);
                    return;
                  }
                  _jspx_tagPool_portlet_renderURL_var.reuse(_jspx_th_portlet_renderURL_11);
                  java.lang.String redirectURL = null;
                  redirectURL = (java.lang.String) _jspx_page_context.findAttribute("redirectURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  portlet:actionURL
                  com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_2 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
                  _jspx_th_portlet_actionURL_2.setPageContext(_jspx_page_context);
                  _jspx_th_portlet_actionURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_portlet_actionURL_2.setName("/document_library/edit_repository");
                  _jspx_th_portlet_actionURL_2.setVar("deleteURL");
                  int _jspx_eval_portlet_actionURL_2 = _jspx_th_portlet_actionURL_2.doStartTag();
                  if (_jspx_eval_portlet_actionURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_55 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_55.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_55.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_55.setName( Constants.CMD );
                    _jspx_th_portlet_param_55.setValue( Constants.DELETE );
                    int _jspx_eval_portlet_param_55 = _jspx_th_portlet_param_55.doStartTag();
                    if (_jspx_th_portlet_param_55.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_55);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_55);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_56 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_56.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_56.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_56.setName("redirect");
                    _jspx_th_portlet_param_56.setValue( (view || folderSelected) ? redirectURL : redirect );
                    int _jspx_eval_portlet_param_56 = _jspx_th_portlet_param_56.doStartTag();
                    if (_jspx_th_portlet_param_56.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_56);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_56);
                    out.write("\n\t\t\t\t\t\t");
                    //  portlet:param
                    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_57 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                    _jspx_th_portlet_param_57.setPageContext(_jspx_page_context);
                    _jspx_th_portlet_param_57.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
                    _jspx_th_portlet_param_57.setName("repositoryId");
                    _jspx_th_portlet_param_57.setValue( String.valueOf(repositoryId) );
                    int _jspx_eval_portlet_param_57 = _jspx_th_portlet_param_57.doStartTag();
                    if (_jspx_th_portlet_param_57.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_57);
                      return;
                    }
                    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_57);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_portlet_actionURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
                    return;
                  }
                  _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
                  java.lang.String deleteURL = null;
                  deleteURL = (java.lang.String) _jspx_page_context.findAttribute("deleteURL");
                  out.write("\n\n\t\t\t\t\t");
                  //  liferay-ui:icon-delete
                  com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_1 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
                  _jspx_th_liferay$1ui_icon$1delete_1.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_icon$1delete_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                  _jspx_th_liferay$1ui_icon$1delete_1.setUrl( deleteURL );
                  int _jspx_eval_liferay$1ui_icon$1delete_1 = _jspx_th_liferay$1ui_icon$1delete_1.doStartTag();
                  if (_jspx_th_liferay$1ui_icon$1delete_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_1);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_icon$1delete_url_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_1);
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                  return;
                }
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                return;
              }
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_16);
            out.write("\n\n\t\t");

		boolean documentLibraryAdmin = portletName.equals(DLPortletKeys.DOCUMENT_LIBRARY_ADMIN);
		boolean hasExportImportPortletInfoPermission = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.EXPORT_IMPORT_PORTLET_INFO);
		boolean inStagingGroup = stagingGroupHelper.isStagingGroup(scopeGroupId);
		boolean portletStaged = stagingGroupHelper.isStagedPortlet(scopeGroupId, DLPortletKeys.DOCUMENT_LIBRARY);
		
            out.write("\n\n\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_17 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_17.setPageContext(_jspx_page_context);
            _jspx_th_c_if_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
            _jspx_th_c_if_17.setTest( (folder != null) && documentLibraryAdmin && hasExportImportPortletInfoPermission && inStagingGroup && portletStaged );
            int _jspx_eval_c_if_17 = _jspx_th_c_if_17.doStartTag();
            if (_jspx_eval_c_if_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:actionURL
              com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_3 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
              _jspx_th_portlet_actionURL_3.setPageContext(_jspx_page_context);
              _jspx_th_portlet_actionURL_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
              _jspx_th_portlet_actionURL_3.setName("/document_library/publish_folder");
              _jspx_th_portlet_actionURL_3.setVar("publishFolderURL");
              int _jspx_eval_portlet_actionURL_3 = _jspx_th_portlet_actionURL_3.doStartTag();
              if (_jspx_eval_portlet_actionURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_58 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_58.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_58.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
                _jspx_th_portlet_param_58.setName("backURL");
                _jspx_th_portlet_param_58.setValue( currentURL );
                int _jspx_eval_portlet_param_58 = _jspx_th_portlet_param_58.doStartTag();
                if (_jspx_th_portlet_param_58.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_58);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_58);
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_59 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_59.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_59.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
                _jspx_th_portlet_param_59.setName("folderId");
                _jspx_th_portlet_param_59.setValue( String.valueOf(folder.getFolderId()) );
                int _jspx_eval_portlet_param_59 = _jspx_th_portlet_param_59.doStartTag();
                if (_jspx_th_portlet_param_59.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_59);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_59);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_portlet_actionURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_3);
                return;
              }
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_3);
              java.lang.String publishFolderURL = null;
              publishFolderURL = (java.lang.String) _jspx_page_context.findAttribute("publishFolderURL");
              out.write("\n\n\t\t\t");
              //  liferay-ui:icon-delete
              com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_2 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
              _jspx_th_liferay$1ui_icon$1delete_2.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon$1delete_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_17);
              _jspx_th_liferay$1ui_icon$1delete_2.setConfirmation("are-you-sure-you-want-to-publish-the-selected-folder");
              _jspx_th_liferay$1ui_icon$1delete_2.setMessage("publish-to-live");
              _jspx_th_liferay$1ui_icon$1delete_2.setUrl( publishFolderURL );
              int _jspx_eval_liferay$1ui_icon$1delete_2 = _jspx_th_liferay$1ui_icon$1delete_2.doStartTag();
              if (_jspx_th_liferay$1ui_icon$1delete_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_2);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon$1delete_url_message_confirmation_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_2);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_if_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_17);
            out.write('\n');
            out.write('	');
            int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_icon$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
        out.write("\n\n\t");
        //  aui:script
        com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
        _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_aui_script_0.setUse("uploader");
        int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_aui_script_0.doInitBody();
          }
          do {
            out.write("\n\t\tif (!A.UA.ios && (A.Uploader.TYPE != 'none')) {\n\t\t\tvar uploadMultipleDocumentsIcon = A.all('.upload-multiple-documents:hidden');\n\n\t\t\tuploadMultipleDocumentsIcon.show();\n\t\t}\n\n\t\tvar slideShow = A.one('.");
            out.print( randomNamespace );
            out.write("-slide-show');\n\n\t\tif (slideShow) {\n\t\t\tslideShow.on(\n\t\t\t\t'click',\n\t\t\t\tfunction(event) {\n\t\t\t\t\t");
            //  portlet:renderURL
            com.liferay.taglib.portlet.RenderURLTag _jspx_th_portlet_renderURL_12 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_portlet_renderURL_windowState_var.get(com.liferay.taglib.portlet.RenderURLTag.class);
            _jspx_th_portlet_renderURL_12.setPageContext(_jspx_page_context);
            _jspx_th_portlet_renderURL_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
            _jspx_th_portlet_renderURL_12.setVar("viewSlideShowURL");
            _jspx_th_portlet_renderURL_12.setWindowState( LiferayWindowState.POP_UP.toString() );
            int _jspx_eval_portlet_renderURL_12 = _jspx_th_portlet_renderURL_12.doStartTag();
            if (_jspx_eval_portlet_renderURL_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              if (_jspx_meth_portlet_param_60((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_renderURL_12, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t");
              //  portlet:param
              com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_61 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
              _jspx_th_portlet_param_61.setPageContext(_jspx_page_context);
              _jspx_th_portlet_param_61.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_12);
              _jspx_th_portlet_param_61.setName("folderId");
              _jspx_th_portlet_param_61.setValue( String.valueOf(folderId) );
              int _jspx_eval_portlet_param_61 = _jspx_th_portlet_param_61.doStartTag();
              if (_jspx_th_portlet_param_61.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_61);
                return;
              }
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_61);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_portlet_renderURL_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_12);
              return;
            }
            _jspx_tagPool_portlet_renderURL_windowState_var.reuse(_jspx_th_portlet_renderURL_12);
            java.lang.String viewSlideShowURL = null;
            viewSlideShowURL = (java.lang.String) _jspx_page_context.findAttribute("viewSlideShowURL");
            out.write("\n\n\t\t\t\t\tvar slideShowWindow = window.open('");
            out.print( viewSlideShowURL );
            out.write("', 'slideShow', 'directories=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no');\n\n\t\t\t\t\tslideShowWindow.focus();\n\t\t\t\t}\n\t\t\t);\n\t\t}\n\t");
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
        out.write('\n');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
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

  private boolean _jspx_meth_portlet_param_2(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_0);
    _jspx_th_portlet_param_2.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_2.setValue("/document_library/edit_folder");
    int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
    if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
    return false;
  }

  private boolean _jspx_meth_portlet_param_6(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_6 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_1);
    _jspx_th_portlet_param_6.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_6.setValue("/document_library/edit_repository");
    int _jspx_eval_portlet_param_6 = _jspx_th_portlet_param_6.doStartTag();
    if (_jspx_th_portlet_param_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_6);
    return false;
  }

  private boolean _jspx_meth_portlet_param_10(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_10 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_2);
    _jspx_th_portlet_param_10.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_10.setValue("/document_library/move_entry");
    int _jspx_eval_portlet_param_10 = _jspx_th_portlet_param_10.doStartTag();
    if (_jspx_th_portlet_param_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_10);
    return false;
  }

  private boolean _jspx_meth_portlet_param_14(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_14 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_3);
    _jspx_th_portlet_param_14.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_14.setValue("/document_library/edit_folder");
    int _jspx_eval_portlet_param_14 = _jspx_th_portlet_param_14.doStartTag();
    if (_jspx_th_portlet_param_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_14);
    return false;
  }

  private boolean _jspx_meth_portlet_param_22(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_22 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_4);
    _jspx_th_portlet_param_22.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_22.setValue("/document_library/edit_folder");
    int _jspx_eval_portlet_param_22 = _jspx_th_portlet_param_22.doStartTag();
    if (_jspx_th_portlet_param_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_22);
    return false;
  }

  private boolean _jspx_meth_portlet_param_27(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_27 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_27.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_5);
    _jspx_th_portlet_param_27.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_27.setValue("/document_library/edit_folder");
    int _jspx_eval_portlet_param_27 = _jspx_th_portlet_param_27.doStartTag();
    if (_jspx_th_portlet_param_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_27);
    return false;
  }

  private boolean _jspx_meth_portlet_param_32(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_32 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_6);
    _jspx_th_portlet_param_32.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_32.setValue("/document_library/edit_repository");
    int _jspx_eval_portlet_param_32 = _jspx_th_portlet_param_32.doStartTag();
    if (_jspx_th_portlet_param_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_32);
    return false;
  }

  private boolean _jspx_meth_portlet_param_35(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_35 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_7);
    _jspx_th_portlet_param_35.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_35.setValue("/document_library/edit_file_entry");
    int _jspx_eval_portlet_param_35 = _jspx_th_portlet_param_35.doStartTag();
    if (_jspx_th_portlet_param_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_35);
    return false;
  }

  private boolean _jspx_meth_portlet_param_39(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_39 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_39.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_8);
    _jspx_th_portlet_param_39.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_39.setValue("/document_library/upload_multiple_file_entries");
    int _jspx_eval_portlet_param_39 = _jspx_th_portlet_param_39.doStartTag();
    if (_jspx_th_portlet_param_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_39);
    return false;
  }

  private boolean _jspx_meth_portlet_param_44(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_44 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_44.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_9);
    _jspx_th_portlet_param_44.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_44.setValue("/document_library/edit_file_shortcut");
    int _jspx_eval_portlet_param_44 = _jspx_th_portlet_param_44.doStartTag();
    if (_jspx_th_portlet_param_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_44);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_44);
    return false;
  }

  private boolean _jspx_meth_portlet_param_60(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_renderURL_12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_60 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_60.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_60.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_renderURL_12);
    _jspx_th_portlet_param_60.setName("mvcRenderCommandName");
    _jspx_th_portlet_param_60.setValue("/image_gallery_display/view_slide_show");
    int _jspx_eval_portlet_param_60 = _jspx_th_portlet_param_60.doStartTag();
    if (_jspx_th_portlet_param_60.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_60);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_60);
    return false;
  }
}
