package utilityPackage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.xpath;

public class TableHelper {

    private List<WebElement> rows;
    private WebElement tableContainer;

    /**
     * Build a TableHelper instance using a tag.
     *
     * @param tableTag (By) The locator tag of the table
     */
    public TableHelper(By tableTag) {
        this(Driver.getDriver().findElement(tableTag));
    }

    /**
     * This constructor populate some info base on the pTableElement
     *
     * @param pTableElement (WebElement) The locator of the table
     */
    public TableHelper(WebElement pTableElement) {

        if (pTableElement != null) {
            tableContainer = pTableElement;

            if (!tableContainer.getTagName().equalsIgnoreCase("table")) {
                WebElement table = tableContainer.findElement(xpath(".//table"));
                if (table != null) {
                    tableContainer = table;
                } else {
                    System.out.println("Unable to find table!");
                }
            }
            init();

        } else {
            System.out.println("Unable to find table!");

        }
    }

    /**
     * Use to reset internal variable, like when we delete a row.
     */
    private void init() {
        rows = null;
    }

    public WebElement getContainer() {
        return tableContainer;
    }

    /**
     * Return list of WebElement rows (TR)
     *
     * @return (List of WebElement)
     */
    public List<WebElement> getRows() {
        if (rows == null) {
            //init rows
            rows = tableContainer.findElements(xpath("./tbody/tr"));
        }
        return rows;
    }

    /**
     * Return the cell text of pRowNo and pColumnNo
     *
     * @param pRowNo    (int) start at 0
     * @param pColumnNo (int) start at 0
     * @return (String)
     */
    public String getCellText(int pRowNo, int pColumnNo) {
        String cell = null;
        if (getRows().size() >= pRowNo) {
            List<String> cellTexts = splitHTMLCellOfRow(getRows().get(pRowNo));
            if (cellTexts.size() >= pColumnNo) {
                cell = cellTexts.get(pColumnNo);
            } else {

                System.out.println(new Exception("The pColumnNo [" + pColumnNo + "] is greater than total of columns [" + cellTexts.size() + "]"));
            }
        } else {
            System.out.println(new Exception("The pRowNo [" + pRowNo + "] is greater than total of rows [" + getRows().size() + "]"));
        }
        return cell;
    }


    /**
     * Return the text of each cell (TD) of the pRow
     *
     * @param pRow (WebElement)
     * @return (List of String)
     */
    public static List<String> splitHTMLCellOfRow(WebElement pRow) {
        Document doc = Jsoup.parseBodyFragment("<table><tr>" + pRow.getAttribute("innerHTML") + "</tr></table>");

        return doc.select("td").stream()
                .map(e -> e.text())
                .collect(toList());
    }
}