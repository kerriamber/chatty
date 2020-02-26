
package chatty.gui.components.menus;

import chatty.gui.UrlOpener;
import chatty.lang.Language;
import chatty.util.MiscUtil;

import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * For adding a copy or copy/cut/paste context menu.
 * 
 * For just setting a single copy/cut/paste context menu to a text component
 * without any custom handling, GuiUtil.addTextContextMenu() can be used.
 *
 * @author tduva
 */
public class TextSelectionMenu extends ContextMenu {

    private final String selectedText;
    
    /**
     * Copy given text to clipboard.
     * 
     * @param selectedText 
     */
    public TextSelectionMenu(String selectedText) {
        this.selectedText = selectedText;
        addItem("copy", Language.getString("textCm.copy"));
        addItem("translate", Language.getString("textCm.translate"));
    }
    
    /**
     * Need to override actionPerformed for custom handling.
     */
    public TextSelectionMenu() {
        addItem("copy", Language.getString("textCm.copy"));
        addItem("cut", Language.getString("textCm.cut"));
        addItem("paste", Language.getString("textCm.paste"));
        addItem("translate", Language.getString("textCm.translate"));
        this.selectedText = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("copy") && selectedText != null) {
            MiscUtil.copyToClipboard(selectedText);
        } else if (e.getActionCommand().equals("translate") && selectedText != null) {
            try {
                UrlOpener.openUrl("https://translate.google.com/#view=home&op=translate&sl=auto&tl=en&text=" +
                        URLEncoder.encode(selectedText, "UTF-8"));
            } catch (UnsupportedEncodingException ignored) {

            }
        }
    }
    
}
