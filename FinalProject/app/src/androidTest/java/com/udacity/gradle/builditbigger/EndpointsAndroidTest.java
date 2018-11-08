package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EndpointsAndroidTest {

    @Test
    public void testMustNotRetriveEmptyJokes() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Context context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue(result.length() > 0);
                countDownLatch.countDown();
            }
        };
        testTask.execute(new Pair<Context, String>(context,""));
        countDownLatch.await();
    }
}
