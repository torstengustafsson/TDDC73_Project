package com.example.administrator.carousel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class MainActivity extends AppCompatActivity {

    Carousel carousel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView text1 = new TextView(this);
        text1.setText("Search for movies:");

        final EditText searchText = new EditText(this);
        searchText.setInputType(InputType.TYPE_CLASS_TEXT);
        searchText.setImeOptions(IME_ACTION_SEARCH);
        searchText.setHint("e.g. 'star wars'");

        carousel = new Carousel(this);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_NULL  ) {
                    searchOMDB(searchText.getText().toString().replaceAll(" ", "%20"));
                    return true;
                }
                return false;
            }
        });

        initCarousel();

        layout.addView(text1);
        layout.addView(searchText);
        layout.addView(carousel);

        setContentView(layout);
    }

    /**
     * Demo function to show how you may bind url:s statically if you don't want to write an
     * image retriever class. Item is a container class used by Carousel
     * that holds a title and an image URL.
     */
    private void initCarousel() {
        ArrayList<Item> exampleCarouselItems = new ArrayList<>();
        exampleCarouselItems.add(new Item("Image 1", "http://www.w3schools.com/css/img_fjords.jpg") );
        exampleCarouselItems.add(new Item("Image 2", "https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg") );
        exampleCarouselItems.add(new Item("Image 3", "https://codepo8.github.io/canvas-images-and-pixels/img/horse.png") );
        exampleCarouselItems.add(new Item("Image 4", "https://cdn.spacetelescope.org/archives/images/publicationjpg/heic0602a.jpg") );
        exampleCarouselItems.add(new Item("Image 5", "http://www.vital-capital.com/media/EARTH_AT_NIGH_363137a.jpg") );
        exampleCarouselItems.add(new Item("Image 6", "http://www.zastavki.com/pictures/originals/2013/Photoshop_Image_of_the_horse_053857_.jpg") );
        exampleCarouselItems.add(new Item("Image 7", "https://www.tes.com/sites/default/files/news_article_images/trump_pic.jpg") );

        carousel.updateResults(exampleCarouselItems);
        carousel.setHeaderText("Some example images");
    }

    /**
     * Creates a request to the OMDB api.
     * OMDBRetriever then tells the carousel to update its values.
     */
    private void searchOMDB(String searchText) {
        OMDBRetriever OMDBRetriever = new OMDBRetriever(getBaseContext(), carousel, 16);
        OMDBRetriever.execute(searchText);
    }
}
