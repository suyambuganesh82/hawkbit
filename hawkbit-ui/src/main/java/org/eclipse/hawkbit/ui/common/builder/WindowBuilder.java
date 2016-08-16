package org.eclipse.hawkbit.ui.common.builder;

import org.eclipse.hawkbit.ui.common.CommonDialogWindow;
import org.eclipse.hawkbit.ui.common.CustomCommonDialogWindow;
import org.eclipse.hawkbit.ui.utils.I18N;
import org.eclipse.hawkbit.ui.utils.SPUIDefinitions;
import org.eclipse.hawkbit.ui.utils.SPUIStyleDefinitions;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

/**
 * Builder for Window.
 */
public class WindowBuilder {

    private String caption;
    private Component content;
    private ClickListener saveButtonClickListener;
    private ClickListener cancelButtonClickListener;
    private String helpLink;
    private AbstractLayout layout;
    private I18N i18n;
    private final String type;
    private String id;

    /**
     * Constructor.
     * 
     * @param type
     *            window type
     */
    public WindowBuilder(final String type) {
        this.type = type;
    }

    /**
     * Set the caption.
     * 
     * @param caption
     *            the caption
     * @return the window builder
     */
    public WindowBuilder caption(final String caption) {
        this.caption = caption;
        return this;
    }

    /**
     * Set the content.
     * 
     * @param content
     *            the content
     * @return the window builder
     */
    public WindowBuilder content(final Component content) {
        this.content = content;
        return this;
    }

    /**
     * Set the saveButtonClickListener.
     * 
     * @param saveButtonClickListener
     *            the saveButtonClickListener
     * @return the window builder
     */
    public WindowBuilder saveButtonClickListener(final ClickListener saveButtonClickListener) {
        this.saveButtonClickListener = saveButtonClickListener;
        return this;
    }

    /**
     * Set the cancelButtonClickListener.
     * 
     * @param cancelButtonClickListener
     *            the cancelButtonClickListener
     * @return the window builder
     */
    public WindowBuilder cancelButtonClickListener(final ClickListener cancelButtonClickListener) {
        this.cancelButtonClickListener = cancelButtonClickListener;
        return this;
    }

    /**
     * Set the helpLink.
     * 
     * @param helpLink
     *            the helpLink
     * @return the window builder
     */
    public WindowBuilder helpLink(final String helpLink) {
        this.helpLink = helpLink;
        return this;
    }

    /**
     * Set the layout.
     * 
     * @param layout
     *            the layout
     * @return the window builder
     */
    public WindowBuilder layout(final AbstractLayout layout) {
        this.layout = layout;
        return this;
    }

    /**
     * Set the i18n.
     * 
     * @param i18n
     *            the i18n
     * @return the window builder
     */
    public WindowBuilder i18n(final I18N i18n) {
        this.i18n = i18n;
        return this;
    }

    /**
     * @param id
     *            the id to set * @return the window builder
     */
    public WindowBuilder id(final String id) {
        this.id = id;
        return this;
    }

    /**
     * Build the common dialog window.
     *
     * @return the window.
     */
    public CommonDialogWindow buildCommonDialogWindow() {
        CommonDialogWindow window;

        if (SPUIDefinitions.CUSTOM_METADATA_WINDOW.equals(type)) {
            window = new CustomCommonDialogWindow(caption, content, helpLink, saveButtonClickListener,
                    cancelButtonClickListener, layout, i18n);
            window.setDraggable(true);
            window.setClosable(true);
            return window;
        }
        window = new CommonDialogWindow(caption, content, helpLink, saveButtonClickListener, cancelButtonClickListener,
                layout, i18n);

        decorateWindow(window);

        return window;

    }

    private void decorateWindow(final Window window) {
        if (id != null) {
            window.setId(id);
        }

        if (SPUIDefinitions.CONFIRMATION_WINDOW.equals(type)) {
            window.setDraggable(false);
            window.setClosable(true);
            window.addStyleName(SPUIStyleDefinitions.CONFIRMATION_WINDOW_CAPTION);

        } else if (SPUIDefinitions.CREATE_UPDATE_WINDOW.equals(type)) {
            window.setDraggable(true);
            window.setClosable(true);
        }
    }

    /**
     * Build window based on type.
     *
     * @return Window
     */
    public Window buildWindow() {
        final Window window = new Window(caption);
        window.setContent(content);
        window.setSizeUndefined();
        window.setModal(true);
        window.setResizable(false);

        decorateWindow(window);
        return window;
    }
}
