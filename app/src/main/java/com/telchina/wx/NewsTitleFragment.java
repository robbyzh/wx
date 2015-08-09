package com.telchina.wx;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zg on 2015/8/9.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private Boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);

        if (isTwoPane) {
            NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(
                    R.id.news_content_fragment);
            fragment.refresh(news.getTitle(), news.getContent());

        } else {
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }

    public List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News("news1news1news1", "news content news content"));
        newsList.add(new News("news2news2news2", "news content news content"));
        newsList.add(new News("news3news3news3", "news content news content"));
        newsList.add(new News("news4news4news4", "news content news content"));

        return newsList;
    }
}
