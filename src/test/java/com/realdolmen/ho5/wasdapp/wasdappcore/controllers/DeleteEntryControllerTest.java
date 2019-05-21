package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.DeleteEntryController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.FireBaseService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeleteEntryControllerTest {

    @Mock
    Model model;

    @Mock
    WasdappService wasdappService;

    @Mock
    FireBaseService fireStoreService;

    @Mock
    CurrentUser currentUser;

    @InjectMocks
    DeleteEntryController deleteEntryController;

    @Test
    public void handleDeleteNoUser() throws InterruptedException, ExecutionException {
        List<Long> list = new ArrayList<>();
        list.add(-1l);
        UserWassdapp user = null;
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verifyZeroInteractions(fireStoreService);
    }

    @Test
    public void handleDeleteNoAdmin() throws InterruptedException, ExecutionException {
        List<Long> list = new ArrayList<>();
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("user");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verifyZeroInteractions(fireStoreService);
    }

    @Test
    public void handleDeleteItemsNoIds() throws ExecutionException, InterruptedException {
        List<Long> list = new ArrayList<>();
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verifyZeroInteractions(fireStoreService);
    }

    @Test
    public void handleDeleteItemsOneId() throws InterruptedException, ExecutionException {
        List<Long> list = new ArrayList<>();
        list.add(22l);
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verify(fireStoreService, times(1)).deleteById(22l);
    }

    @Test
    public void handleDeleteItemsMultipleIds() throws InterruptedException, ExecutionException {
        List<Long> list = new ArrayList<>();
        list.add(22l);
        list.add(123l);
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verify(fireStoreService, times(1)).deleteById(22l);
        verify(fireStoreService, times(1)).deleteById(123l);

    }

}
