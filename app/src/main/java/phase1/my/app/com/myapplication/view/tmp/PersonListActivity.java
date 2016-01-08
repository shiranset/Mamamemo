package phase1.my.app.com.myapplication.view.tmp;

/**
 * Created by Owner on 23/10/2014.
 */
/*public class PersonListActivity extends ListActivity {

    private static final int CFG_PERSON = 1;
    private static final String fileName = "ayeletfile";


    public PersonListActivity() {

    }


    protected void onCreate(Bundle savedInstanceState) {
        MapAdapter<Integer, PersonItem> adapter = new MapAdapter<Integer, PersonItem>(this);
        Collection<PersonItem> items = load(this,fileName);
        for (PersonItem p : items) {
            adapter.add(p);
        }
        setListAdapter(adapter);
        setContentView(R.layout.person_list);
        super.onCreate(savedInstanceState);
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                PersonItem p = (PersonItem)view.getTag();
                editPerson(p);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_person_new: {
                newPerson();
                return true;
            }
            case R.id.action_memo_new: {
                newMemo();
                return true;
            }

            default:
                return true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CFG_PERSON:
                if (resultCode == RESULT_OK) {
                    PersonItem person = (PersonItem)data.getSerializableExtra(PersonConfigActivity.DATA_EXTRA_PERSON);
                    MapAdapter<Integer, PersonItem> adapter = ((MapAdapter<Integer, PersonItem>)this.getListAdapter());
                    int actionId = (Integer)data.getSerializableExtra(PersonConfigActivity.DATA_ACTION_ID);
                    if (actionId == R.id.person_action_delete) {
                        adapter.remove(person);
                    } else {
                        adapter.add(person);
                    }

                    saveToFile(this, fileName, adapter.getItems());
                }
                break;
        }

    }


    private void editPerson(PersonItem person) {
        Intent intent = new Intent(this, EditPersonConfigActivity.class);
        intent.putExtra(PersonConfigActivity.DATA_EXTRA_PERSON, person);
        startActivityForResult(intent, CFG_PERSON);
    }

    private void newPerson() {
        Intent intent = new Intent(this, NewPersonConfigActivity.class);
        startActivityForResult(intent, CFG_PERSON);
    }

    private void newMemo() {
        Intent intent = new Intent(this, NewMemoActivity.class);
        startActivity(intent);
    }

    public static ImmutableCollection<PersonItem> load(Context context, String fileName) {

        try {
            XmlItems<PersonItem> items = new XmlItems<PersonItem>("PERSONS");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream inputStream =  context.getApplicationContext().openFileInput(fileName);
            Document dom = builder.parse(inputStream);
            Element root = dom.getDocumentElement();
            return items.loadFromXML(root, PersonItem.getXmlBuilder());
        } catch (Exception e) {
            Log.e("AppCfgManager.load:", "Failed to save to file" + fileName, e);
        }
        return null;
    }

    private static void saveToFile(Context context, String fileName, ImmutableCollection<PersonItem> items) {
        try {

            XmlItems<PersonItem> xmlItems = new XmlItems<PersonItem>("PERSONS");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("APP_ROOT");
            Element elem = xmlItems.toXML(doc, PersonItem.getXmlBuilder(), items);

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

    *//*final PersonCfgView.PersonDialog p = person == null ? PersonCfgView.createDialog(PersonListActivity.this) : PersonCfgView.editDialog(PersonListActivity.this, person);
        p.setCancelable(false);
        p.setOnDismissListener(new DialogInterface.OnDismissListener() {


            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Person person = PersonCfgView.createPerson(p);

                ((ArrayAdapter)PersonListActivity.this.getListAdapter()).add(person.getDesc(PersonListActivity.this));
                //cfgManager.add(person, PersonListActivity.this);
            }
        });
        p.show();*//*




}*/
