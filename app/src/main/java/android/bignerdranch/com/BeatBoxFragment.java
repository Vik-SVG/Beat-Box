package android.bignerdranch.com;

import android.bignerdranch.com.databinding.FragmentBeatBoxBinding;
import android.bignerdranch.com.databinding.ListItemSoundBinding;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import java.util.List;

import static android.widget.SeekBar.*;


public class BeatBoxFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private BeatBox mBeatBox;
    private String mSpeedRate;

    // TODO: Rename and change types of parameters


    public BeatBoxFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
      //  Bundle args = new Bundle();
      //  args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBeatBox = new BeatBox(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentBeatBoxBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        int maxSeekBarSpeed = binding.seekBarPlaybackSpeed.getMax();
        binding.playbackSpeed.setText("Speed is " + maxSeekBarSpeed+"%");

        binding.seekBarPlaybackSpeed.setProgress(maxSeekBarSpeed /2);
        binding.seekBarPlaybackSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float result;
                if (progress < seekBar.getMax() / 2) {
                    result = progress * 0.01f + 0.5f;
                    mBeatBox.setRate(result);
                } else {
                    result = progress * 0.02f;
                    mBeatBox.setRate(result);
                }
                mSpeedRate = (int)(result * 100) + "%";
                binding.playbackSpeed.setText("Speed is " + mSpeedRate);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

                // mSpeedRate = (String.valueOf(seekBar.getProgress()));

            }


        });
        return binding.getRoot();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mBeatBox.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        private SoundHolder(ListItemSoundBinding binding){
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind (Sound sound){
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }

    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound>sounds){
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder (ViewGroup parent, int viewType){
            Context context;
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);

        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }


    }


}
