package phase1.my.app.com.myapplication.view.tmp;

/**
 * Created by Owner on 23/10/2014.
 */
/*
public class TmpPersonListFragment extends ListFragment {

    private static final int CFG_PERSON = 1;
    public static final String DATA_EXTRA_PERSON = "PERSON";
    private static final String fileName = "ayeletfile";


    public TmpPersonListFragment() {

    }

    @Override
    public void onViewCreated(View  view , android.os.Bundle savedInstanceState) {
        MapAdapter<Integer, PersonItemView> adapter = new MapAdapter<Integer, PersonItemView>(this.getActivity());
        Collection<PersonItem> items = load(this.getActivity(),fileName);
        for (PersonItem p : items) {
            adapter.add(new PersonItemView(p, this));
        }
        setListAdapter(adapter);
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
        this.getListView();


    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, android.view.MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.person_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_person_new: {
                newPerson();
                return true;
            }
            default:
                return true;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CFG_PERSON && resultCode == Activity.RESULT_OK) {

            PersonItem person = (PersonItem) data.getSerializableExtra(PersonConfigFragment.DATA_EXTRA_PERSON);
            if (person == null) {
                return;
            }
            MapAdapter<Integer, PersonItemView> adapter = ((MapAdapter<Integer, PersonItemView>) this.getListAdapter());
            int actionId = (Integer) data.getSerializableExtra(PersonConfigFragment.DATA_ACTION_ID);
            if (actionId == R.id.person_action_delete) {
                adapter.remove(new PersonItemView(person, this));
            } else {
                adapter.add(new PersonItemView(person, this));
            }
            ImmutableCollection<PersonItemView> collection = adapter.getItems();
            Collection newCollection = new ArrayList<PersonItem>();
            for (PersonItemView view : collection) {
                newCollection.add(view);
            }
            saveToFile(this.getActivity(), fileName, ImmutableList.copyOf(newCollection));
            adapter.notifyDataSetChanged();
        }

    }

    private void newPerson() {
        Intent intent = new Intent(this.getActivity(), PersonConfigActivity.class);
        startActivityForResult(intent, CFG_PERSON);
    }



    public ImmutableCollection<PersonItem> load(Context context, String fileName) {

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

    private void saveToFile(Context context, String fileName, ImmutableCollection<PersonItem> items) {
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

    private static class PersonItemView implements ViewItemAdapterI, MapAdapter.KeyItem<Integer> {

        private final PersonItem personItem;
        private final ListFragment listFragment;

        private PersonItemView(PersonItem personItem, ListFragment lstFragment) {
            this.personItem = personItem;
            this.listFragment = lstFragment;
        }

        @Override
        public View getView(Context context, View convertView, ViewGroup parent) {
            if( convertView == null ) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.person_list_item, parent, false);
            }
            Button personButton = (Button) convertView.findViewById(R.id.person_info_button);
            TextView textView = (TextView) convertView.findViewById(R.id.text_view);
            textView.setText(personItem.getPerson().toString());
            personButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editPerson(personItem);
                }
            });

            RadioButton radioButton = (RadioButton)convertView.findViewById(R.id.radio_button);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            return convertView;
        }

        public void editPerson(PersonItem person) {
            Intent intent = new Intent(listFragment.getActivity(), PersonConfigActivity.class);
            intent.putExtra(PersonConfigFragment.DATA_EXTRA_PERSON, person);
            listFragment.startActivityForResult(intent, CFG_PERSON);
        }

        protected void finish() {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(DATA_EXTRA_PERSON, personItem);
            listFragment.getActivity().setResult(Activity.RESULT_OK, returnIntent);
            listFragment.getActivity().finish();
        }

        @Override
        public Integer getKey() {
            return personItem.getKey();
        }
    }


}
*/
