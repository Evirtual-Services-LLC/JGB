package com.evs.jgb.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.evs.jgb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailsFragment extends Fragment {
    public static final String BUNDLE_KEY = "key";
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    String details;
    @BindView(R.id.textHeading)
    TextView textHeading;
    @BindView(R.id.textDetail)
    TextView textDetail;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;

        // Intent intent = new Intent(ArticlesActivity.this,DetailsActivity.class);
        // intent.putExtra("details","article");
        // startActivity(intent);
    }

    private void intialize() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            details = bundle.getString(BUNDLE_KEY);

        }
        if (details.equalsIgnoreCase("PARENTING DETAILS")) {
            textHeading.setText("Adjusting to Single Parenthood");
            textDetail.setText("Mothers and fathers that find themselves in the new role of being a single parent have many adjustments to make in their lives. It can often be overwhelming, but there are some things you can do to make the transition easier for both you and your children:" +
                    "\n\n Give yourself time to adjust to your new role as a single parent and to grieve for the loss of your relationship with your partner. Your children will need time to adjust as well.\n" +
                    "Do not consider yourself a failure because of a failed relationship. Focus on the positive things about you and your children.\n\n" +
                    "Make a conscious effort to go forward with your life. Living in the past will not help you. You have to focus on the new life you and your children are now leading.");
        }else if (details.equalsIgnoreCase("E-LEARNING")) {
            textHeading.setText("Effective Communication");
            textDetail.setText("The ability to effectively communicate with others is one of the most powerful" +
                    " tools for personal and professional success. Most people are challenged by the many day-to-day " +
                    "interactions with coworkers, family, and friends. Emotion, communication, and conflict are present " +
                    "in all human interactions and affect each of us in different ways. Everyone manages emotion, communication, " +
                    "and conflict from habit—patterns and styles developed early in life and over time. In this eLearning" +
                    " you will learn how to more effectively communicate in both personal and work situations.");
        }else if (details.equalsIgnoreCase("LEGALFORMS")) {
            textHeading.setText("Children's Carpool Agreement");
            textDetail.setText("Documents are saved in rtf (rich text format) so that you can save the text into almost " +
                    "all versions of any word processor and on any computer operating system. They are provided so that " +
                    "you can then make changes to the document content if you want to.The files are provided on this Web" +
                    " site so you can get immediate access to the information. They can be saved on your computer and " +
                    "viewed with any word processor, including Wordpad, Notepad, Word and Star Office. The files are " +
                    "named so that they end with the file extension .rtf.");
        }else if (details.equalsIgnoreCase("ONLINE SEMINAR")) {
            textHeading.setText("Online Seminar");
            textDetail.setText("For many people across the world, the annual summer vacation has been canceled, and they" +
                    " are facing the prospect of staying at home with the family for the holiday season. While this can " +
                    "feel disappointing and frustrating, there is no need to write off the prospect of a holiday " +
                    "altogether! Planning the ultimate staycation can help provide that long-awaited downtime with your " +
                    "family and ensure that everyone gets a chance to have some fun in their own hometown.");
        }else if (details.equalsIgnoreCase("RESOURCES")) {

            textHeading.setText("Resources");
            textDetail.setText("The American Pregnancy Association is a nonprofit, national health organization committed " +
                    "to promoting reproductive and pregnancy wellness through education, research, advocacy, and community " +
                    "awareness. It's website provides lots of information about avoiding, planning, and supporting a healthy " +
                    "pregnancy; reproductive health,birth planning, and much more.");

        }
//        if (details.equalsIgnoreCase("1")) {
//            textHeading.setText("Foster Parents Considering Adoption");
//            textDetail.setText("<b>Differences Between Foster Parenting and Adopting</b>\n" +
//                    "There are a number of significant differences between foster care and adoption for the " +
//                    "foster and adoptive family involved, even when a child remains in the same household. " +
//                    "Compared to foster care, adoption brings the following changes for the parents:\n\n " +
//                    "Full Legal Responsibility for a Child—Legal responsibility was held by the agency during " +
//                    "the time the child was in foster care.\n\n  Full Financial Responsibility for the Child—Even if the " +
//                    "family receives adoption assistance or a subsidy on behalf of the child, families are still responsible " +
//                    "for financial obligations such as child care and extracurricular activities.");
//        }
//        if (details.equalsIgnoreCase("2")) {
//            textHeading.setText("Legal Glossary A - D");
//            textDetail.setText("341 Meeting\n" +
//                    "In a bankruptcy proceeding, a meeting of creditors at which the debtor is questioned under " +
//                    "oath by creditors, a trustee, an examiner, or the U.S. Trustee about his or her financial affairs.");
//        }
//        if (details.equalsIgnoreCase("3")) {
//            textHeading.setText("Adoption Provider Locator");
//            textDetail.setText("Important information about Adoption Referrals: Our online databases are designed " +
//                    "for individuals who would prefer to search for programs and services on their own. The Adoption " +
//                    "locator database provides a list of services, professionals, community support groups and important " +
//                    "resources, as well as information on international adoptions. These listings include provider " +
//                    "names and contact information.");
//        }
//        if (details.equalsIgnoreCase("4")) {
//            textHeading.setText("Adopt America Network");
//            textDetail.setText("The Adopt America Network's mission focuses on placing special needs children in permanent" +
//                    " homes. The site provides information about the organization, a sample listing of waiting children, " +
//                    "a list of agencies, and access to online application forms.");
//        }
//        if (details.equalsIgnoreCase("5")) {
//            textHeading.setText("Basic Child Care Considerations");
//            textDetail.setText("There are many important factors to consider when choosing child care. Here are some " +
//                    "of the basics to consider when you are just beginning the process:");
//        }
//        if (details.equalsIgnoreCase("6")) {
//            textHeading.setText("Child Care Handbook");
//            textDetail.setText("Adobe Acrobat Reader, available for free from Adobe, allows you to view, navigate, and " +
//                    "print PDF files across all major computing platforms.\n" + "\n" +
//                    "If you already have a PDF reader installed, just click the link to view the document, otherwise " +
//                    "click the Adobe icon to download and install the PDF reader.");
//
//        }
//        if (details.equalsIgnoreCase("7")) {
//            textHeading.setText("Authorization for Foreign Travel With a Minor");
//            textDetail.setText("Documents are saved in rtf (rich text format) so that you can save the text into almost" +
//                    " all versions of any word processor and on any computer operating system. They are provided so that you" +
//                    " can then make changes to the document content if you want to.The files are provided on this Web site so " +
//                    "you can get immediate access to the information. They can be saved on your computer and viewed with any word " +
//                    "processor, including Wordpad, Notepad, Word and Star Office. The files are named so that they end with the" +
//                    " file extension .rtf.");
//
//        }
//        if (details.equalsIgnoreCase("8")) {
//            textHeading.setText("Child Care Regulations");
//            textDetail.setText("Each state in the U.S. manages licensure of child care settings in different ways. In order" +
//                    " to offer you access to information on your state, we have provided a link to the National Database of" +
//                    " Child Care Licensing Regulations website. This is maintained by the National Center on Early Childhood" +
//                    " Quality Assurance (NCECQA), which is funded by the Administration for Children and Families, U.S. " +
//                    "Department of Health & Human Services. The primary mission is to promote health and safety in" +
//                    " out-of-home child care settings throughout the nation. The licensure regulations from the 50 states" +
//                    " and the District of Columbia are available on this website. The NCECQA updates this database as " +
//                    "changes are made.");
//        }

    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText(details);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}