package android.bignerdranch.com;

import android.content.Context;
import android.media.SoundPool;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BeatBoxTest {

    private BeatBox mSubject;
    private Sound mSound;
    private Integer mSoundId;
    private SoundPool mSoundPool;
    private Context mContext;

    @Before
    public void setUp() throws Exception {
    mSound = mock(Sound.class);


    mSoundId = mSound.getSoundId();

   mSubject = mock(BeatBox.class);

    mSoundPool = mSubject.getSoundPool();

    }


    @Test
    public void testPlayButton(){


        assertThat(mSoundPool, is(mSubject.getSoundPool()));

        mSubject.release();

        verify(mSubject).release();



    }

}