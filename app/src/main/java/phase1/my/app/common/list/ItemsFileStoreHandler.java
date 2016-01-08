package phase1.my.app.common.list;

import android.content.Context;
import android.util.Log;

import com.google.common.collect.ImmutableCollection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import phase1.my.app.common.xml.XmlItemI;
import phase1.my.app.common.xml.XmlItems;

/**
 * Created by Owner on 07/12/2014.
 */
public class ItemsFileStoreHandler<E> {

    private final XmlItemI<E> xmlItemI;
    private final String elementsTag;

    public ItemsFileStoreHandler(XmlItemI<E> xmlItemI, String elementsTag) {
        this.xmlItemI = xmlItemI;
        this.elementsTag = elementsTag;
    }

    public ImmutableCollection<E> load(String fileName, Context context) {

        try {
            XmlItems<E> items = new XmlItems<E>(elementsTag);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(fileName);
            FileInputStream inputStream =  context.getApplicationContext().openFileInput(fileName);
            Document dom = builder.parse(inputStream);
            Element root = dom.getDocumentElement();
            return items.loadFromXML(root, xmlItemI);
        } catch (Exception e) {
            Log.e("AppCfgManager.load:", "Failed to save to file" + fileName, e);
        }
        return null;
    }

    public void saveToFile(String fileName, Context context, ImmutableCollection<E> items) {
        try {

            XmlItems<E> xmlItems = new XmlItems<E>(elementsTag);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("APP_ROOT");
            Element elem = xmlItems.toXML(doc, xmlItemI, items);

            root.appendChild(elem);
            doc.appendChild(root);

            FileOutputStream outputStream =   context.openFileOutput(fileName,Context.MODE_PRIVATE);
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.transform(domSource, result);
            outputStream.write(writer.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("AppCfgManager.save:", "Failed to save to file" + fileName, e);
        }
    }
}
