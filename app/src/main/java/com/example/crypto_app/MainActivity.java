package com.example.crypto_app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener {

    CardView totalcardview, cardtotal;
    FloatingActionButton btnRefresh;
    TextView Totaltextview;

    //variabili nuovo layout
    TextView nomeAstarnew, nomeBttnew, nomeAtomnew, nomeDotnew, nomeHuahuanew, nomeJunonew, nomeOsmonew, nomeScrtnew;
    TextView quantitaastarnew, quantitabttnew, quantitaatomnew, quantitadotnew, quantitahuahuanew, quantitajunonew, quantitaosmonew, quantitascrtnew;
    TextView prezzoastarnew, prezzobttnew, prezzoatomnew, prezzodotnew, prezzohuahuanew, prezzojunonew, prezzoosmonew, prezzoscrtnew;
    TextView totaleastarnew, totalebttnew, totaleatomnew, totaledotnew, totalehuahuanew, totalejunonew, totaleosmonew, totalescrtnew;
    MaterialButton editbtnastarnew, editbtnbttnew, editbtnatomnew, editbtndotnew, editbtnhuahuanew, editbtnjunonew, editbtnosmonew, editbtnscrtnew;


    double liquiditadisponibile = 1000;
    double importoinvestito = 10000;

    //E' sottolineato perchè è deprecato con le nuove versioni
    ProgressDialog pd;

    //sono variabili che servono a salvare i nuovi valori dopo l edit

    Double quantitamodificaastar = 100.0;
    Double  quantitamodificabtt = 1000000.0;
    Double  quantitamodificaatom = 100.0;
    Double  quantitamodificadot = 100.0;
    Double  quantitamodificahuahua = 100.0;
    Double  quantitamodificajuno = 100.0;
    Double  quantitamodificaosmo = 100.0;
    Double   quantitamodificascrt = 100.0;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cardtotal = findViewById(R.id.cardtotal);
        editbtnbttnew = findViewById(R.id.editbtnbtt);

        //Nuovo Layout----------------------------->
        nomeAstarnew = findViewById(R.id.nomeAstar);
        nomeBttnew = findViewById(R.id.nomeBtt);
        nomeAtomnew = findViewById(R.id.nomeAtom);
        nomeDotnew = findViewById(R.id.nomeDot);
        nomeHuahuanew = findViewById(R.id.nomeHuahua);
        nomeJunonew = findViewById(R.id.nomeJuno);
        nomeOsmonew = findViewById(R.id.nomeOsmo);
        nomeScrtnew = findViewById(R.id.nomeScrt);

        quantitaastarnew = findViewById(R.id.quantitaastar);
        quantitabttnew = findViewById(R.id.quantitabtt);
        quantitaatomnew = findViewById(R.id.quantitaatom);
        quantitadotnew = findViewById(R.id.quantitadot);
        quantitahuahuanew = findViewById(R.id.quantitahuahua);
        quantitajunonew = findViewById(R.id.quantitajuno);
        quantitaosmonew = findViewById(R.id.quantitaosmo);
        quantitascrtnew = findViewById(R.id.quantitascrt);

        prezzoastarnew = findViewById(R.id.prezzoastar);
        prezzobttnew = findViewById(R.id.prezzobtt);
        prezzoatomnew = findViewById(R.id.prezzoatom);
        prezzodotnew = findViewById(R.id.prezzodot);
        prezzohuahuanew = findViewById(R.id.prezzohuahua);
        prezzojunonew = findViewById(R.id.prezzojuno);
        prezzoosmonew = findViewById(R.id.prezzoosmo);
        prezzoscrtnew = findViewById(R.id.prezzoscrt);

        totaleastarnew = findViewById(R.id.totaleastar);
        totalebttnew = findViewById(R.id.totalebtt);
        totaleatomnew = findViewById(R.id.totaleatom);
        totaledotnew = findViewById(R.id.totaledot);
        totalehuahuanew = findViewById(R.id.totalehuahua);
        totalejunonew = findViewById(R.id.totalejuno);
        totaleosmonew = findViewById(R.id.totaleosmo);
        totalescrtnew = findViewById(R.id.totalescrt);
        //----------------------------->


        btnRefresh = findViewById(R.id.btnHit);
        totalcardview = findViewById(R.id.cardtotal);
        Totaltextview = findViewById(R.id.Totaltextview);

        new JsonTask().execute("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=ATOM,ASTAR,DOT,SCRT,OSMO,JUNO,HUAHUA,BTT&CMC_PRO_API_KEY="INSERIREKEYCMC"&convert=eur");


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantitamodificaastar == 0 || quantitamodificabtt == 0 || quantitamodificaatom == 0 || quantitamodificadot == 0
                        || quantitamodificahuahua == 0 || quantitamodificajuno == 0 || quantitamodificaosmo == 0 || quantitamodificascrt == 0) {
                    Toast.makeText(MainActivity.this, "Devi inserire prima tutte le quantità",
                            Toast.LENGTH_LONG).show();
                } else {
                    YoYo.with(Techniques.RotateIn).duration(700).playOn(findViewById(R.id.btnHit));
                    new JsonTask().execute("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=ATOM,ASTAR,DOT,SCRT,OSMO,JUNO,HUAHUA,BTT&CMC_PRO_API_KEY=0cb512c8-39c7-4bdf-9c8a-df16ae3e780f&convert=eur");

                }

            }
        });


        editbtnbttnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Modifica");
    }


    @Override
    public void applyModify(String astar, String btt, String atom, String dot, String huahua, String juno, String osmo, String scrt) {
        quantitaastarnew.setText("Quantità: " + astar);
        quantitabttnew.setText("Quantità: " + btt);
        quantitaatomnew.setText("Quantità: " + atom);
        quantitadotnew.setText("Quantità: " + dot);
        quantitahuahuanew.setText("Quantità: " + huahua);
        quantitajunonew.setText("Quantità: " + juno);
        quantitaosmonew.setText("Quantità: " + osmo);
        quantitascrtnew.setText("Quantità: " + scrt);

        double parseAstar = Double.parseDouble(astar);
        double parseBtt = Double.parseDouble(btt);
        double parseAtom = Double.parseDouble(astar);
        double parseDot = Double.parseDouble(dot);
        double parseHuahua = Double.parseDouble(huahua);
        double parseJuno = Double.parseDouble(juno);
        double parseOsmo = Double.parseDouble(osmo);
        double parseScrt = Double.parseDouble(scrt);

        quantitamodificaastar = parseAstar;
        quantitamodificabtt = parseBtt;
        quantitamodificaatom = parseAtom;
        quantitamodificadot = parseDot;
        quantitamodificahuahua = parseHuahua;
        quantitamodificajuno = parseJuno;
        quantitamodificaosmo = parseOsmo;
        quantitamodificascrt = parseScrt;
    }

    //Visualizzazione del popup mentre aggiorna i dati
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Prego attendere, caricamento dati...");
            pd.setCancelable(false);
            pd.show();
        }

        //gestione della richiesta con url
        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d(">RISPOSTA: ", "> " + line);

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        //Eseguita dopo aver correttamente preso la richiesta dell'URL
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }
            try {
                JSONObject QueryCompleta = (JSONObject) new JSONTokener(result).nextValue(); //Lettura di tutto il file json
                JSONObject Data = QueryCompleta.getJSONObject("data"); //Lettura di tutti i valori riferiti alle coin, è escluso il campo status presente nel json


                JSONObject QueryAstar = Data.getJSONObject("ASTAR"); //Con questa query si filtrano i valori per ogni singola coin, qui c è tutto riguardante ASTAR
                JSONObject QueryAtom = Data.getJSONObject("ATOM");
                JSONObject QueryBtt = Data.getJSONObject("BTT");
                JSONObject QueryDot = Data.getJSONObject("DOT");
                JSONObject QueryHuahua = Data.getJSONObject("HUAHUA");
                JSONObject QueryJuno = Data.getJSONObject("JUNO");
                JSONObject QueryOsmo = Data.getJSONObject("OSMO");
                JSONObject QueryScrt = Data.getJSONObject("SCRT");

                //ASTAR------------------->
                JSONObject QueryAstarQuote = QueryAstar.getJSONObject("quote");
                JSONObject QueryAstarEUR = QueryAstarQuote.getJSONObject("EUR");

                //ATOM------------------->
                JSONObject QueryAtomQuote = QueryAtom.getJSONObject("quote");
                JSONObject QueryAtomEUR = QueryAtomQuote.getJSONObject("EUR");

                //BTT------------------->
                JSONObject QueryBttQuote = QueryBtt.getJSONObject("quote");
                JSONObject QueryBttEUR = QueryBttQuote.getJSONObject("EUR");

                //DOT------------------->
                JSONObject QueryDotQuote = QueryDot.getJSONObject("quote");
                JSONObject QueryDotEUR = QueryDotQuote.getJSONObject("EUR");

                //HUAHUA------------------->
                JSONObject QueryHuahuaQuote = QueryHuahua.getJSONObject("quote");
                JSONObject QueryHuahuaEUR = QueryHuahuaQuote.getJSONObject("EUR");

                //JUNO------------------->
                JSONObject QueryJunoQuote = QueryJuno.getJSONObject("quote");
                JSONObject QueryJunoEUR = QueryJunoQuote.getJSONObject("EUR");

                //OSMO------------------->
                JSONObject QueryOsmoQuote = QueryOsmo.getJSONObject("quote");
                JSONObject QueryOsmoEUR = QueryOsmoQuote.getJSONObject("EUR");

                //SCRT------------------->
                JSONObject QueryScrtQuote = QueryScrt.getJSONObject("quote");
                JSONObject QueryScrtEUR = QueryScrtQuote.getJSONObject("EUR");


                //Prelevo il simbolo(nome) e il valore del prezzo
                //ASTAR------------------->
                String nomeAstar = (String) QueryAstar.getString("symbol");
                Log.d("NOME ASTAR", nomeAstar);
                double prezzoAstar = QueryAstarEUR.getDouble("price");
                prezzoAstar = 0.10; //aggiunto io perchè coinmarketcap non ha ancora listato sulle api il prezzo


                //ATOM------------------->
                String nomeAtom = (String) QueryAtom.getString("symbol");
                double prezzoAtom = QueryAtomEUR.getDouble("price");

                //BTT------------------->
                String nomeBtt = (String) QueryBtt.getString("symbol");
                double prezzoBtt = QueryBttEUR.getDouble("price");

                //DOT------------------->
                String nomeDot = (String) QueryDot.getString("symbol");
                double prezzoDot = QueryDotEUR.getDouble("price");

                //HUAHUA------------------->
                String nomeHuahua = (String) QueryHuahua.getString("symbol");
                double prezzoHuahua = QueryHuahuaEUR.getDouble("price");

                //JUNO------------------->
                String nomeJuno = (String) QueryJuno.getString("symbol");
                double prezzoJuno = QueryJunoEUR.getDouble("price");

                //OSMO------------------->
                String nomeOsmo = (String) QueryOsmo.getString("symbol");
                double prezzoOsmo = QueryOsmoEUR.getDouble("price");

                //SCRT------------------->
                String nomeScrt = (String) QueryScrt.getString("symbol");
                double prezzoScrt = QueryScrtEUR.getDouble("price");


                //Formattazione della stringa per ridurre i caratteri del double

                //BTT---->
                double totalebtt = quantitamodificabtt * prezzoBtt;
                String totalebttFormatted = String.format("%.2f", totalebtt);
                //ATOM---->
                double totaleatom = quantitamodificaatom * prezzoAtom;
                String totaleatomFormatted = String.format("%.2f", totaleatom);
                //ASTAR---->
                double totaleastar = quantitamodificaastar * prezzoAstar;
                Log.d("TOTALEASTARPRIMADIFORMATTED", String.valueOf(totaleastar));

                String totaleastarFormatted = String.format("%.2f", totaleastar);
                //DOT---->
                double totaledot = quantitamodificadot * prezzoDot;
                String totaledotFormatted = String.format("%.2f", totaledot);
                //SCRT---->
                double totalescrt = quantitamodificascrt * prezzoScrt;
                String totalescrtFormatted = String.format("%.2f", totalescrt);
                //HUAHUA---->
                double totalehuahua = quantitamodificahuahua * prezzoHuahua;
                String totalehuahuaFormatted = String.format("%.2f", totalehuahua);
                //JUNO---->
                double totalejuno = quantitamodificajuno * prezzoJuno;
                String totalejunoFormatted = String.format("%.2f", totalejuno);
                //OSMO---->
                double totaleosmo = quantitamodificaosmo * prezzoOsmo;
                String totaleosmoFormatted = String.format("%.2f", totaleosmo);


                //Formattiamo per non visualizzare troppe cifre
                //ATOM------------------->
                String prezzoatomFormatted = String.format("%.2f", prezzoAtom);
                //ASTAR------------------->
                String prezzoastarFormatted = String.format("%.3f", prezzoAstar);
                //BTT------------------->
                String prezzobttFormatted = String.format("%.7f", prezzoBtt);
                //DOT------------------->
                String prezzodotFormatted = String.format("%.2f", prezzoDot);
                //SCRT------------------->
                String prezzoscrtFormatted = String.format("%.2f", prezzoScrt);
                //JUNO------------------->
                String prezzojunoFormatted = String.format("%.2f", prezzoJuno);
                //OSMO------------------->
                String prezzoosmoFormatted = String.format("%.2f", prezzoOsmo);
                //HUAHUA------------------->
                String prezzohuahuaFormatted = String.format("%.5f", prezzoHuahua);


                //TOTALE DI TUTTE LE COIN
                double Price_ALL = totaleastar + totaleatom + totalebtt + totaledot + totalejuno + totaleosmo + totalescrt + totalehuahua + liquiditadisponibile;
                String Price_ALL_Formatted = String.format("%.2f", Price_ALL);

                //PROFITTO---->
                double total = Price_ALL - importoinvestito;
                String totaleFormatted = String.format("%.2f", total);


                //ASSOCIAZIONE LAYOUT NUOVO ----------------------------->

                nomeAstarnew.setText(nomeAstar.toUpperCase());
                quantitaastarnew.setText("Quantità: " + quantitamodificaastar);
                prezzoastarnew.setText("Prezzo: " + prezzoastarFormatted + " €");
                totaleastarnew.setText("Totale: " + totaleastarFormatted + " €");

                nomeBttnew.setText(nomeBtt.toUpperCase());
                quantitabttnew.setText("Quantità: " + quantitamodificabtt);
                prezzobttnew.setText("Prezzo: " + prezzobttFormatted + " €");
                totalebttnew.setText("Totale: " + totalebttFormatted + " €");

                nomeAtomnew.setText(nomeAtom.toUpperCase());
                quantitaatomnew.setText("Quantità: " + quantitamodificaatom);
                prezzoatomnew.setText("Prezzo: " + prezzoatomFormatted + " €");
                totaleatomnew.setText("Totale: " + totaleatomFormatted + " €");

                nomeDotnew.setText(nomeDot.toUpperCase());
                quantitadotnew.setText("Quantità: " + quantitamodificadot);
                prezzodotnew.setText("Prezzo: " + prezzodotFormatted + " €");
                totaledotnew.setText("Totale: " + totaledotFormatted + " €");

                nomeHuahuanew.setText(nomeHuahua.toUpperCase());
                quantitahuahuanew.setText("Quantità: " + quantitamodificahuahua);
                prezzohuahuanew.setText("Prezzo: " + prezzohuahuaFormatted + " €");
                totalehuahuanew.setText("Totale: " + totalehuahuaFormatted + " €");

                nomeJunonew.setText(nomeJuno.toUpperCase());
                quantitajunonew.setText("Quantità: " + quantitamodificajuno);
                prezzojunonew.setText("Prezzo: " + prezzojunoFormatted + " €");
                totalejunonew.setText("Totale: " + totalejunoFormatted + " €");

                nomeOsmonew.setText(nomeOsmo.toUpperCase());
                quantitaosmonew.setText("Quantità: " + quantitamodificaosmo);
                prezzoosmonew.setText("Prezzo: " + prezzoosmoFormatted + " €");
                totaleosmonew.setText("Totale: " + totaleosmoFormatted + " €");

                nomeScrtnew.setText(nomeScrt.toUpperCase());
                quantitascrtnew.setText("Quantità: " + quantitamodificascrt);
                prezzoscrtnew.setText("Prezzo: " + prezzoscrtFormatted + " €");
                totalescrtnew.setText("Totale: " + totalescrtFormatted + " €");
                //--------------------------------------->

                if (importoinvestito < Price_ALL) {
                    Totaltextview.setText("Importo investito : " + importoinvestito + " €" + "\n" + "Liquidità disponibile : " + liquiditadisponibile + " €" + "\n" + "Totale coin : " + Price_ALL_Formatted + " €" + "\n" + "Sei in profitto di ~ : " + " " + totaleFormatted + " €");
                    cardtotal.setCardBackgroundColor(getResources().getColor(R.color.md_green_200));

                } else {
                    Totaltextview.setText("Importo investito : " + importoinvestito + " €" + "\n" + "Liquidità disponibile : " + liquiditadisponibile + " €" + "\n" + "Totale coin : " + Price_ALL_Formatted + " €" + "\n" + "Sei in perdita di ~ : " + " " + totaleFormatted + " €");
                    cardtotal.setCardBackgroundColor(getResources().getColor(R.color.md_red_200));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
