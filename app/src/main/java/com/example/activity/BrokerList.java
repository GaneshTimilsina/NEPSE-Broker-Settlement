package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.class_model.BrokerListModel;
import com.example.nepse_brokersettlement.R;
import com.example.others.BrokerListAdapter;

import java.util.ArrayList;

public class BrokerList extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<BrokerListModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;


    public final String[] broker_no = {
            "1","3","4","5","6","7","8","10","11","13","14","16","17","18","19","20","21","22","25","26","28","29","32","33","34","35","36","37","38","39",
            "40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","D01"
    };


    public final String[] broker_name = {
            "Kumari Securities Pvt. Limited", "Arun Securities Pvt. Limited","Opal Securities Investment Pvt. Limited", "Market Securities Exchange Company Pvt. Limited",
            "Agrawal Securities Pvt. Limited","J.F. Securities Company Pvt. Limited", "Ashutosh Brokerage & Securities Pvt. Limited","Pragyan Securities Pvt. Limited",
            "Malla & Malla Stock Broking Company Pvt. Limited","Thrive Brokerage House Pvt. Limited","Nepal Stock House Pvt. Limited","Primo Securities Pvt. Limited",
            "ABC Securities Pvt. Limited","Sagarmatha Securities Pvt. Limited","Nepal Investment & Securities Trading Pvt. Limited","Sipla Securities Pvt. Limited",
            "Midas Stock Broking Company Pvt. Limited","Siprabi Securities Pvt. Limited","Sweta Securities Pvt. Limited","Asian Securities Pvt. Limited",
            "Shree Krishna Securities Limited","Trishul Securities And Investment Limited","Premier Securites Company Limited","Dakshinkali Investment Securities Pvt.Limited",
            "Vision Securities Pvt.Limited","Kohinoor Investment and Securities Pvt.Ltd","Secured Securities Limited","Swarnalaxmi Securities Pvt.Limited","Dipshika Dhitopatra Karobar Co. Pvt.Limited",
            "Sumeru Securities Pvt.Limited","Creative Securities Pvt.Limited","Linch Stock Market Limited","Sani Securities Company Limited","South Asian Bulls Pvt.Limited",
            "Dynamic Money Managers Securities Pvt.Ltd","Imperial Securities Co .Pvt.Limited","Kalika Securities Pvt.Limited","Neev Securities Pvt.Ltd","Trishakti Securities Public Limited",
            "Online Securities Pvt.Ltd","Crystal Kanchenjunga Securities Pvt.Ltd","Oxford Securities Pvt.Ltd","Sundhara Securities Limited","Investment Management Nepal Pvt. Ltd.",
            "Sewa Securities Pvt. Ltd.","Bhrikuti Stock Broking Co. Pvt. Ltd.","Shree Hari Securities Pvt.Ltd","Araya Tara Investment And Securities Pvt. Ltd.","Naasa Securities Co. Ltd.",
            "Deevyaa Securities & Stock House Pvt. Ltd","Nagarik Stock Dealer Company Ltd."


    };


    public final String[] address = {
            "Dillibazar, Kathmandu", "Putalisadak, Kathmandu", "Uttardhoka,Kathmandu" ,"Kichha Pokhari,Kathmandu","Dillibazar, Kathmandu","Newroad,Kathmandu",
            "Battisputali, Kathmandu", "Kamaladi, Kathmandu","Hattisar, Kathmandu","Naxal,Kathmandu", "Kalikasthan,Kathmandu", "Putalisadak, Kathmandu", "Indrachowk, Kathmandu",
            "Dillibazar, Kathmandu","Main Road, Biratnagar","NewRoad,Kathmandu","Putalisadak, Kathmandu","Pulchowk, Lalitpur","Putalisadak,Kathmandu","Putalisadak-32, Kathmandu",
            "Dillibazar, Kathmandu","Putalisadak, Kathmandu","Putalisadak,Kathmandu","Kamaladi, Kathmandu","Anamnagar, Kathmandu","Hattisar Sadak, Kathmandu",
            "Kathmandu-28","Putalisadak, Kathmandu","Anamnagar, Kathmandu","Hattisar, Kathmandu","Kamalpokhari-28, Kathmandu","New Baneshwor, Kathmandu",
            "Jamal, Kathmandu","Kuleshwor-14, Kathmandu","Kamalpokhari, Kathmandu","Anamnagar, Kathmandu","Anamnagar, Kathmandu","Putalisadak, Kathmandu","Putalisadak, Kathmandu",
            "Putalisadak, Kathmandu","Jaljala Chowk, Biratnagar","Kalimati, Kathmandu","Sundhara, Kathmandu","Tripureshwor,Kathmandu","Tripureshwor,Kathmandu",
            "New Road, Kathmandu,","Kamaladi, Kathmandu","Anamnagar, Kathmandu","Naxal, Kathmandu","Putalisadak, Kathmandu","X"
    };


    public final String[] ph_no = {
            "01-4418036", "01-4239567", "01-4420313,","01-4249558","01-4424657","01-4212072","01-4490233", "01-4413392","01-4414008","01-4416018","01-4429631",
            "01-4168164","01-4266507","01-4433316","01-4480072","01-4255782"," 01-4240115","01-5530701","01-4223778","01-4524485","01-4441225","01-4440709",
            "01-4432700","01-4168641","01-4770408","01-4442858","01-4224523","01-4168291","01-4102534","01-4424209","01-4419582","01-4469068","01-4166006",
            "01- 4284786","01-4413421","01-5706004","01-5706004","01-4168572","01-4168572","01-4168298","9851098200","01-4273850","01-4260174","01-4256590",
            "01-4256644","01-4224648","01-4437466","01-5705596","01-4440384","01-4421488","X"
    };


    public final String[] branches = {
            "New Road, Pokhara", "X","X","X","Main Road, Biratnagar","X","United Insurance Building, Itahari","College Road, Biratnagar","X","Gairipatan, Pokhara",
            "Gairapatan, Pokhara", "Adarsha Nagar, Birgunj","Prithvi Marg, Dharan","X","Main Road, Biratnagar","X","Chauraha, Butwal","Resham Kothi, Birgunj",
            "Ghantaghar, Birgunj","Palpa, Butwal","Municipality Office, Biratnagar","Newroad, Pokhara","New Road, Pokhara","Palpa Road, Butwal","Narayangarh, Bhairawa",
            "New Road, Pokhara","Birtamode, Jhapa","Sangam Chowk, Hetauda","Purano Bhansar Road, Dhangadi","Birtamode, Jhapa","Near to Neco Insurance, Benepa",
            "Milanchowk, Butwal","Sahidchowk, Narayanghat","Birtamod, Jhapa","Deva Bikas Bank, Banepa","Jumla Road, Surkhet","Newroad, Nepalgunj","Biratnagar, Ithari",
            "New Road, Pokhara","College Road, Dharan","New Plaza, Kathmandu","Lions Chowk, Narayangard","Nabil Bank Building, Besisahar","Newroad, Pokhara",
            "Mainroad, Hetauda","Kasturi Chowk, Ithari","Birtamode, Jhapa","Traffic Chowk, Butwal","Adarsha Nagar, Birgunj","Purano Buspark, Baglung","X"
    };


    public final String[] website = {
            "https://kumarisecurities.com/", "http://arunsecurities.com.np/","https://stockbrokeropalsecurities.com/", "X","http://www.agrawalsecurities.com.np/",
            "https://jfsecurities.com/","https://www.ashutoshsecurities.com/","https://pragyansecurities10.com.np/","http://mallastock.com/","https://thrivebrokerage.com/",
            "http://stockhouse.com.np/", "http://primosecurities.com.np/home.php","https://abcsecurities.com.np/index.html","http://sagarmathasecurities.com/",
            "http://nist19.com/","https://siplasecurities.com/","http://midasstock.com.np/home.aspx#","https://siprabi.com/","https://www.swetasecurities.com/",
            "http://aspl26.com/","X","X","https://premier-securites-coltd.business.site/","http://displtd33.com/","https://sharebroker34.com/","http://kohinoorinvestment.com/",
            "https://www.ssl.com.np/","http://www.swarnalaxmisecurities.com.np/","https://ddkc.com.np/","https://www.sumerusecurities.com/","X","http://www.linch.com.np/",
            "https://sanisecurities.com.np/","https://www.southasianbulls.com/","http://dynamic44.com/","http://imperialsecurities.com.np/","https://kalikasecurities.com/",
            "http://neevsecurities.com.np/","https://trishakti.com.np/","http://onlinesecurities.com.np/","http://crystalsecurities.com/","X","http://sundharasecurities.com.np/",
            "X","http://sewasecurities.com/","http://www.bhrikutistock.com/","http://sriharisecurities.com.np/","https://aryatarasecurities.com/","http://www.naasasecurities.com.np/",
            "http://deevyaasecurities.com.np/","X"

    };


    public final String[] latiitude = {
            "27.706093341577358","27.705749324020612","27.71816841223791","27.70245094972633","27.706702332794833","27.7025818472186","27.70300613539786","27.709895427418616",
            "27.711138558877323","27.71431292124532","27.704216446351925","27.702846446616547","27.706758936070507","27.702548954386156","27.69944584889913","27.7100299302908",
            "27.707800139473278","27.675764793478546","27.703994943983453","27.702797043646676","27.708526740165507","27.70143974793967","27.702846446616547","27.708790732959578",
            "27.699809651079093","27.710785021247485","27.702022450440563","27.70268215199545","27.697457255034","27.710344832408104","27.710221732344895","27.690375666951088",
            "27.714278559616574","27.69310286536048","27.710181937408517","27.694470136168405","27.69339159162238","27.709645706798163","27.70329685509762","27.702931205847534",
            "26.46080370988632","27.697257688700777","27.698284993812226","27.695448261649236","27.6949846208305","27.701863546209616","27.710086864859687","27.698651195332374",
            "27.712984922521844","27.701767875161647","27.698181261568656"

    };


    public final String[] longitude = {
            "85.32420590355272","85.3478425043776","85.32265143321219","85.31102081849356","85.32860610470992","85.30872763243495","85.34048284808367","85.32225660451638",
            "85.32130847181656","85.32731364855212","85.32674338980662","85.32196046131588","85.30947119078435","85.325890544464","85.34326937763507","85.32620230413197",
            "85.3219074188868","85.31572514597926","85.32246370374611","85.32328019078318","85.327332501211","85.32370929039179","85.32196046131588","85.32182060612612",
            "85.32814501729148","85.32191507823237","85.32178154541363","85.32194960259943","85.32858226150873","85.33271959039698","85.32467365953228","85.33575743166085",
            "85.32933390135156","85.2982495429471","85.32291563009038","85.327659305807","85.32770176364699","85.3215474445349","85.32229773828402","85.32186455270082",
            "87.28162803351945","85.29562485246089","85.311955595452","85.31106786447093","85.31053646019583","85.3218021436547","85.32227678258305","85.3288158465303",
            "85.3245102002702","85.32450471494272","85.31903384848684"

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        toolbar.setTitle("Broker Details");
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        arrayList.add(new BrokerListModel("1","2",R.drawable.phone,"3","4","4"));

        for (int i = 0; i<broker_no.length; i++){
            arrayList.add(new BrokerListModel(broker_no[i],broker_name[i],R.drawable.phone,address[i], ph_no[i],branches[i], website[i], latiitude[i],longitude[i]));
        }

        BrokerListAdapter adapter = new BrokerListAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);




    }
}