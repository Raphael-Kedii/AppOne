package com.globomed.julyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;
    private MaterialButton buttonActionSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);
        buttonActionSkip = findViewById(R.id.buttonActionSkip);

        setupOnboardingItems();
        savePrefsData();

        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter );

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else{
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                    finish();
                }
            }
        });
        buttonActionSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
    }
    private void setupOnboardingItems(){
        List<Onboardingitem> onboardingitems = new ArrayList<>();

        Onboardingitem itemPayOnline = new Onboardingitem();
        itemPayOnline.setTitle("Pay Your Bill Online");
        itemPayOnline.setDescription("Electric bill payment is a feature of online, mobile and telephone banking.");
        itemPayOnline.setImage(R.drawable.pay_online);

        Onboardingitem itemOnTheWay = new Onboardingitem();
        itemOnTheWay.setTitle("Your Food Is On The way");
        itemOnTheWay.setDescription("Our delivery rider is on the way to deliver your order");
        itemOnTheWay.setImage(R.drawable.on_the_way);

        Onboardingitem itemEatTogether = new Onboardingitem();
        itemEatTogether.setTitle("Eat Together");
        itemEatTogether.setDescription("Enjoy your meal and have a great day. Don't forget to rate us.");
        itemEatTogether.setImage(R.drawable.eat_together);

        onboardingitems.add(itemPayOnline);
        onboardingitems.add(itemOnTheWay);
        onboardingitems.add(itemEatTogether);

        onboardingAdapter = new OnboardingAdapter(onboardingitems);
    }
    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for(int i=0; i<indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i=0; i<childCount; i++){
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount()-1)
        {
            buttonOnboardingAction.setText("Start");
        } else{
            buttonOnboardingAction.setText("Next");
        }
    }
    private void savePrefsData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }
}
