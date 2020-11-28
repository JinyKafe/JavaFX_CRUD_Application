package com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.center;

import com.hexenwerk.tutorial.javafx.crm.client.AbstractModelView;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.left.PersonDetailMV;
import com.hexenwerk.tutorial.javafx.crm.domain.Person;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PersonListMV extends AbstractModelView<List<Person>, PersonListVC> {

    private final ObservableList<PersonDetailMV> personObservableList = FXCollections.observableArrayList(userModel ->
            new Observable[]{userModel.firstNameProperty(),
                    userModel.lastNameProperty(),
                    userModel.birthDayProperty()});

    private final ObjectProperty<PersonDetailMV> currentPersonProperty = new SimpleObjectProperty<>(null);

    private ChangeListener<PersonDetailMV> personSelectionListener;
    private ChangeListener<PersonDetailMV> currentPersonChangedListener;

    public ObjectProperty<PersonDetailMV> currentPersonProperty() {
        return currentPersonProperty;
    }

    public final PersonDetailMV getCurrentPersonModelView() {
        return currentPersonProperty().get();
    }

    public final void setCurrentPersonModelView(PersonDetailMV person) {
        currentPersonProperty().set(person);
    }

    public ObservableList<PersonDetailMV> getPersonObservableList() {
        return personObservableList;
    }

    @Override
    public void merge() {
        super.model = personObservableList
                .stream()
                .map(AbstractModelView::getModel)
                .collect(Collectors.toList());
    }

    @Override
    public void load() {
        Objects.requireNonNull(super.model, "Set model first!");
        personObservableList.remove(0, personObservableList.size());
        personObservableList.addAll(super.model.stream()
                .map((Person person) -> {
                    PersonDetailMV personModelView = new PersonDetailMV();
                    personModelView.setModel(person);
                    return personModelView;
                }).collect(Collectors.toList()));
    }

    @Override
    public void bind(PersonListVC view) {

        view.tableView
                .setItems(this.getPersonObservableList());

        view.tableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> this.currentPersonProperty.set(newSelection));

        this.currentPersonProperty
                .addListener((obs, oldPerson, newPerson) -> {
                    if (newPerson == null) {
                        view.tableView.getSelectionModel().clearSelection();
                    } else {
                        PersonDetailMV selectedPerson = view.tableView.getSelectionModel().getSelectedItem();
                        if (selectedPerson != newPerson) {
                            view.tableView.getSelectionModel().select(newPerson);
                        }
                    }
                });
    }

    @Override
    public void unbind(PersonListVC view) {
        view.tableView.getSelectionModel().selectedItemProperty().removeListener(personSelectionListener);
        this.currentPersonProperty().removeListener(currentPersonChangedListener);
        view.clear();
    }
}