package android.bignerdranch.com;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SoundTest {

    private String mAssetPath;
    private Sound mSubject;

    @Before
    public void setUp() throws Exception {

        mAssetPath = "assetPath";
        mSubject = new Sound(mAssetPath);

    }

    @Test
    public void standartTest(){

        assertThat(mSubject.getSoundId(), is(mSubject.getSoundId()));

        mSubject.setSoundId(54);

        assertThat(mSubject.getSoundId(), is(54));
        System.out.println(mSubject.getName());
       // verify(mSubject).getSoundId();



    }

}