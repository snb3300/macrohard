package payapp.macrohard.com.payapp.ui.activity;

/**
 * Created by nishant on 9/6/15.
 */

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import payapp.macrohard.com.payapp.R;

public class ScreenSlidePageFragment extends Fragment {

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;
    Typeface font_sspsemibold;
    DisplayMetrics metrics;
    int width = 0, height = 0;
    private String[] mIntroStringArray = {
            "Text 1",
            "Text 2",
            "Text 3",
            "Text 4"
    };

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        height = metrics.heightPixels;
//        width = metrics.widthPixels;
//        Toast.makeText(getActivity(),""+height+ " "+width,Toast.LENGTH_LONG).show();
    }

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //font_sspsemibold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SourceSansPro-Semibold.ttf");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        // Set the title view to show the page number.
        //((TextView) rootView.findViewById(R.id.label_introtext1)).setText(mIntroStringArray[mPageNumber]);
        //TextView introText = (TextView) rootView.findViewById(R.id.label_introtext1);
        //introText.setText(mIntroStringArray[mPageNumber]);
       // introText.setTypeface(font_sspsemibold);
      //  ImageView backImage = (ImageView) rootView.findViewById(R.id.image_detail_logo);
//        backImage.getLayoutParams().width = width;
//        backImage.getLayoutParams().height = height;

        ImageView imgView = (ImageView) rootView.findViewById(R.id.imageView2);
        switch(mPageNumber) {
            case 1:
                //imgView.setImageResource(R.drawable.icon1);
                break;
            case 2:
                //imgView.setImageResource(R.drawable.icon1);
                break;
            case 3:
                //imgView.setImageResource(R.drawable.icon1);
                break;
            case 0:
            default:
                //imgView.setImageResource(R.drawable.icon1);
                break;
        }
        return rootView;
    }
}
