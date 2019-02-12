package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import com.google.common.truth.Truth;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WasdappEntryRepositoryTest {

    @Autowired
    private WasdappEntryRepository wasdappEntryRepository;

    @Test
    public void name() {
        Truth.assertThat(wasdappEntryRepository.findById(1L).isPresent()).isFalse();
    }
}
