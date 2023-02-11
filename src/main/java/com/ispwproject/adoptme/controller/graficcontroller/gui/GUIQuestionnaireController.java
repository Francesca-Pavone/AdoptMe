package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;

import com.ispwproject.adoptme.controller.appcontroller.QuestionnaireResultController;
import com.ispwproject.adoptme.engineering.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.engineering.builder.QuestionnaireResultBeanBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIQuestionnaireController {
    @FXML
    private ToggleButton btnDog;
    @FXML
    private ToggleButton btnCat;
    @FXML
    private ToggleButton btnFemale;
    @FXML
    private ToggleButton btnMale;
    @FXML
    private ToggleButton btnGenderNotImportant;
    @FXML
    private ToggleButton btnPuppy;
    @FXML
    private ToggleButton btnYoung;
    @FXML
    private ToggleButton btnAdult;
    @FXML
    private ToggleButton btnSenior;
    @FXML
    private ToggleButton btnAgeNotImportant;
    @FXML
    private ToggleButton btnHaveAPet;
    @FXML
    private ToggleButton btnDontHaveAPet;
    @FXML
    private RadioButton btnMaleCat;
    @FXML
    private RadioButton btnFemaleCat;
    @FXML
    private RadioButton btnMaleDog;
    @FXML
    private RadioButton btnFemaleDog;
    @FXML
    private ToggleButton btnSleepOutside;
    @FXML
    private ToggleButton btnNoSleepOutside;
    @FXML
    private ToggleButton btnHoursAloneOne;
    @FXML
    private ToggleButton btnHoursAloneTwo;
    @FXML
    private ToggleButton btnHoursAloneThree;
    @FXML
    private ToggleButton btnFirstPet;
    @FXML
    private ToggleButton btnNoFirstPet;
    @FXML
    private ToggleButton btnSterilizePet;
    @FXML
    private ToggleButton btnNoSterilizePet;
    @FXML
    private ToggleButton btnProgramEducation;
    @FXML
    private ToggleButton btnNoProgramEducation;
    @FXML
    private ToggleButton btnDisabledPet;
    @FXML
    private ToggleButton btnNoDisabledPet;
    @FXML
    private ToggleButton btnSpecificArea;
    @FXML
    private ToggleButton btnNoSpecificArea;
    @FXML
    private ToggleButton btnSmall;
    @FXML
    private ToggleButton btnMedium;
    @FXML
    private ToggleButton btnLarge;
    @FXML
    private ToggleButton btnExtraLarge;
    @FXML
    private ToggleGroup petAgeGroup;
    @FXML
    private TextField cityTextField;
    @FXML
    private ToggleGroup disabledPetGroup;
    @FXML
    private ToggleGroup firstPetGroup;
    @FXML
    private ToggleGroup hoursAloneGroup1;
    @FXML
    private ToggleGroup petSizeGroup;
    @FXML
    private ToggleGroup programEducationGroup;
    @FXML
    private ToggleGroup specificAreaGroup;
    @FXML
    private ToggleGroup sterilizePetGroup;
    @FXML
    private ToggleGroup sleepOutsideGroup;
    @FXML
    private ToggleGroup petGenderGroup;
    @FXML
    private VBox vboxAlreadyHaveAPet;
    @FXML
    private VBox vboxCity;
    @FXML
    private VBox vboxDisabledPet;
    @FXML
    private VBox vboxFirstPet;
    @FXML
    private VBox vboxGeographicalArea;
    @FXML
    private VBox vboxHoursAlone;
    @FXML
    private VBox vboxParent;
    @FXML
    private VBox vboxPetAge;
    @FXML
    private VBox vboxPetAlreadyHave;
    @FXML
    private VBox vboxPetGender;
    @FXML
    private VBox vboxProgramEducation;
    @FXML
    private VBox vboxSelectPetType;
    @FXML
    private VBox vboxSterilizePet;
    @FXML
    private VBox vboxSleepOutside;
    @FXML
    private VBox vboxDogSize;
    @FXML
    private Button btnEndQuestionnaire;

    List<VBox> vboxList = new ArrayList<>();
    private int petType;

    @FXML
    private ToggleButton btnSizeNotImportant;
    private Parent currentPage;
    private Parent previousPage;

    public void initialize() {
        vboxList.add(vboxSelectPetType);
        vboxList.add(vboxPetGender);
        vboxList.add(vboxPetAge);
        vboxList.add(vboxDogSize);
        vboxList.add(vboxAlreadyHaveAPet);
        vboxList.add(vboxPetAlreadyHave);
        vboxList.add(vboxSleepOutside);
        vboxList.add(vboxHoursAlone);
        vboxList.add(vboxFirstPet);
        vboxList.add(vboxSterilizePet);
        vboxList.add(vboxProgramEducation);
        vboxList.add(vboxDisabledPet);
        vboxList.add(vboxGeographicalArea);
        vboxList.add(vboxCity);

        vboxParent.getChildren().removeAll(vboxPetGender, vboxPetAge, vboxDogSize, vboxAlreadyHaveAPet, vboxPetAlreadyHave, vboxSleepOutside, vboxHoursAlone, vboxFirstPet, vboxSterilizePet, vboxProgramEducation, vboxDisabledPet, vboxGeographicalArea, vboxCity, btnEndQuestionnaire);
    }

    public void removeNextNodes(int i) {
        int j = i + 1;
        int size = vboxList.size();
        while (j < size) {
            if(vboxParent.getChildren().contains(vboxList.get(j)))
                vboxParent.getChildren().remove(vboxList.get(j));
            if(vboxParent.getChildren().contains(btnEndQuestionnaire))
                vboxParent.getChildren().remove(btnEndQuestionnaire);
            j++;
        }
    }

    public void selectDogType() {
        petType = 0;
        if(!vboxParent.getChildren().contains(vboxPetGender) && btnDog.isSelected())
            vboxParent.getChildren().add(vboxPetGender);
        else if(!btnDog.isSelected()) {
            btnDog.setSelected(true);
        }
    }

    public void selectCatType() {
        petType = 1;
        if(vboxParent.getChildren().contains(vboxProgramEducation))
            vboxParent.getChildren().remove(vboxProgramEducation);
        if(!vboxParent.getChildren().contains(vboxPetGender) && btnCat.isSelected())
            vboxParent.getChildren().add(vboxPetGender);
        else if(!btnCat.isSelected()) {
            btnCat.setSelected(true);
        }
    }

    public void selectFemale() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnFemale.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnFemale.isSelected()) {
            btnFemale.setSelected(true);
        }
    }

    public void selectMale() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnMale.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnMale.isSelected()) {
            btnMale.setSelected(true);
        }
    }

    public void selectGenderNotImportant() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnGenderNotImportant.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnGenderNotImportant.isSelected()) {
            btnGenderNotImportant.setSelected(true);
        }
    }

    public void selectPuppy() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnPuppy.isSelected() && petType == 0)
            vboxParent.getChildren().add(vboxDogSize);
        else if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnPuppy.isSelected() && petType == 1)
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnPuppy.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectYoung() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnYoung.isSelected() && petType == 0)
            vboxParent.getChildren().add(vboxDogSize);
        else if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnYoung.isSelected() && petType == 1)
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnYoung.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectAdult() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnAdult.isSelected() && petType == 0)
            vboxParent.getChildren().add(vboxDogSize);
        else if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnAdult.isSelected() && petType == 1)
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnAdult.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectSenior() {
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnSenior.isSelected() && petType == 0)
            vboxParent.getChildren().add(vboxDogSize);
        else if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnSenior.isSelected() && petType == 1)
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnSenior.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectAgeNotImportant() {
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnAgeNotImportant.isSelected() && petType == 0)
            vboxParent.getChildren().add(vboxDogSize);
        else if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnAgeNotImportant.isSelected() && petType == 1)
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnAgeNotImportant.setSelected(true);
        }
        btnPuppy.setSelected(false);
        btnYoung.setSelected(false);
        btnAdult.setSelected(false);
        btnSenior.setSelected(false);
    }

    public void selectSmall() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnSmall.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnSmall.isSelected()) {
            btnSmall.setSelected(true);
        }
    }
    public void selectMedium() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnMedium.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnMedium.isSelected()) {
            btnMedium.setSelected(true);
        }
    }
    public void selectLarge() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnLarge.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnLarge.isSelected()) {
            btnLarge.setSelected(true);
        }
    }
    public void selectExtraLarge() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnExtraLarge.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnSmall.isSelected()) {
            btnExtraLarge.setSelected(true);
        }
    }

    public void selectSizeNotImportant() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnSizeNotImportant.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnSmall.isSelected() && !btnMedium.isSelected() && !btnLarge.isSelected() && !btnExtraLarge.isSelected() && !btnSizeNotImportant.isSelected()) {
            btnSizeNotImportant.setSelected(true);
        }
        btnSmall.setSelected(false);
        btnMedium.setSelected(false);
        btnLarge.setSelected(false);
        btnExtraLarge.setSelected(false);
    }

    public void selectHaveAPet() {
        if(vboxParent.getChildren().contains(vboxSleepOutside) && btnHaveAPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxAlreadyHaveAPet));
            vboxParent.getChildren().add(vboxPetAlreadyHave);
        }
        else if(!vboxParent.getChildren().contains(vboxPetAlreadyHave) && btnHaveAPet.isSelected()) {
            vboxParent.getChildren().add(vboxPetAlreadyHave);
        }
        else if(!btnHaveAPet.isSelected()) {
            btnHaveAPet.setSelected(true);
        }
    }

    public void selectDontHaveAPet() {
        if(vboxParent.getChildren().contains(vboxPetAlreadyHave) && btnDontHaveAPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxAlreadyHaveAPet));
            vboxParent.getChildren().add(vboxSleepOutside);
        }
        else if(!vboxParent.getChildren().contains(vboxSleepOutside) && btnDontHaveAPet.isSelected()) {
            vboxParent.getChildren().add(vboxSleepOutside);
        }
        else if(!btnDontHaveAPet.isSelected()) {
            btnDontHaveAPet.setSelected(true);
        }
    }

    public void selectMaleCat() {
        if(!vboxParent.getChildren().contains(vboxSleepOutside) && btnMaleCat.isSelected())
            vboxParent.getChildren().add(vboxSleepOutside);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnMaleCat.setSelected(true);
        }
    }
    public void selectFemaleCat() {
        if(!vboxParent.getChildren().contains(vboxSleepOutside) && btnFemaleCat.isSelected())
            vboxParent.getChildren().add(vboxSleepOutside);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnFemaleCat.setSelected(true);
        }
    }
    public void selectMaleDog() {
        if(!vboxParent.getChildren().contains(vboxSleepOutside) && btnMaleDog.isSelected())
            vboxParent.getChildren().add(vboxSleepOutside);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnMaleDog.setSelected(true);
        }
    }
    public void selectFemaleDog() {
        if(!vboxParent.getChildren().contains(vboxSleepOutside) && btnFemaleDog.isSelected())
            vboxParent.getChildren().add(vboxSleepOutside);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnFemaleDog.setSelected(true);
        }
    }

    public void selectSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnSleepOutside.isSelected()) {
            btnSleepOutside.setSelected(true);
        }
    }

    public void selectNoSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnNoSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnNoSleepOutside.isSelected()) {
            btnNoSleepOutside.setSelected(true);
        }
    }

    public void selectHoursAloneOne() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneOne.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneOne.isSelected()) {
            btnHoursAloneOne.setSelected(true);
        }
    }

    public void selectHoursAloneTwo() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneTwo.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneTwo.isSelected()) {
            btnHoursAloneTwo.setSelected(true);
        }
    }

    public void selectHoursAloneThree() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneThree.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneThree.isSelected()) {
            btnHoursAloneThree.setSelected(true);
        }
    }

    public void selectNoFirstPet() {
        if(!vboxParent.getChildren().contains(vboxSterilizePet) && btnNoFirstPet.isSelected())
            vboxParent.getChildren().add(vboxSterilizePet);
        else if(!btnNoFirstPet.isSelected()) {
            btnNoFirstPet.setSelected(true);
        }
    }

    public void selectFirstPet() {
        if(!vboxParent.getChildren().contains(vboxSterilizePet) && btnFirstPet.isSelected())
            vboxParent.getChildren().add(vboxSterilizePet);
        else if(!btnFirstPet.isSelected()) {
            btnFirstPet.setSelected(true);
        }
    }

    public void selectSterilizePet() {
        if(petType == 0 && !vboxParent.getChildren().contains(vboxProgramEducation) && btnSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxProgramEducation);
        if(petType == 1 && !vboxParent.getChildren().contains(vboxDisabledPet) && btnSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnSterilizePet.isSelected()) {
            btnSterilizePet.setSelected(true);
        }
    }

    public void selectNoSterilizePet() {
        if(petType == 0 && !vboxParent.getChildren().contains(vboxProgramEducation) && btnNoSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxProgramEducation);
        if(petType == 1 && !vboxParent.getChildren().contains(vboxDisabledPet) && btnNoSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnNoSterilizePet.isSelected()) {
            btnNoSterilizePet.setSelected(true);
        }
    }

    public void selectProgramEducation() {
        if(!vboxParent.getChildren().contains(vboxDisabledPet) && btnProgramEducation.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnProgramEducation.isSelected()) {
            btnProgramEducation.setSelected(true);
        }
    }

    public void selectNoProgramEducation() {
        if(!vboxParent.getChildren().contains(vboxDisabledPet) && btnNoProgramEducation.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnNoProgramEducation.isSelected()) {
            btnNoProgramEducation.setSelected(true);
        }
    }

    public void selectDisabledPet() {
        if(!vboxParent.getChildren().contains(vboxGeographicalArea) && btnDisabledPet.isSelected())
            vboxParent.getChildren().add(vboxGeographicalArea);
        else if(!btnDisabledPet.isSelected()) {
            btnDisabledPet.setSelected(true);
        }
    }

    public void selectNoDisabledPet() {
        if(!vboxParent.getChildren().contains(vboxGeographicalArea) && btnNoDisabledPet.isSelected())
            vboxParent.getChildren().add(vboxGeographicalArea);
        else if(!btnNoDisabledPet.isSelected()) {
            btnNoDisabledPet.setSelected(true);
        }
    }

    public void selectSpecificArea() {
        if(vboxParent.getChildren().contains(btnEndQuestionnaire) && btnSpecificArea.isSelected()) {
            vboxParent.getChildren().remove(btnEndQuestionnaire);
            vboxParent.getChildren().add(vboxCity);
        }
        if(!vboxParent.getChildren().contains(vboxCity) && btnSpecificArea.isSelected())
            vboxParent.getChildren().add(vboxCity);
        else if(!btnSpecificArea.isSelected()) {
            btnSpecificArea.setSelected(true);
        }
    }

    public void selectNoSpecificArea() {
        if(vboxParent.getChildren().contains(vboxCity) && btnNoSpecificArea.isSelected()) {
            vboxParent.getChildren().remove(vboxCity);
        }
        if(!vboxParent.getChildren().contains(btnEndQuestionnaire) && btnNoSpecificArea.isSelected())
            vboxParent.getChildren().add(btnEndQuestionnaire);
        else if(!btnNoSpecificArea.isSelected()) {
            btnNoSpecificArea.setSelected(true);
        }
    }

    public void searchCity() {
        if(!vboxParent.getChildren().contains(btnEndQuestionnaire))
            vboxParent.getChildren().add(btnEndQuestionnaire);
    }

    public void goBack() throws IOException {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ExitQuestionnaire.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());

            dialog.setScene(scene1);
            dialog.show();
    }

    public void endQuestionnaire(ActionEvent event) throws Exception {
        QuestionnaireResultBeanBuilder questionnaireResultBeanBuilder = QuestionnaireResultBeanBuilder.newQuestionnaireResultBean()
                .type(petType)
                .gender(switch (((ToggleButton) petGenderGroup.getSelectedToggle()).getText()) {
                            case "Female" -> 1;
                            case "Male" -> 0;
                            default -> -1;
                        })
                .sleepOutside(switch (((ToggleButton) sleepOutsideGroup.getSelectedToggle()).getText()) {
                    case "Yes" -> 1;
                    default -> 0;
                })
                .hoursAlone(switch (((ToggleButton) hoursAloneGroup1.getSelectedToggle()).getText()) {
                    case "4 - 6 hours" -> 1;
                    case "More than 6 hours" -> 2;
                    default -> 0;
                })
                .firstPet(switch (((ToggleButton) firstPetGroup.getSelectedToggle()).getText()) {
                    case "No" -> 0;
                    default -> 1;
                })
                .sterilizePet(switch (((ToggleButton) sterilizePetGroup.getSelectedToggle()).getText()) {
                    case "Yes" -> true;
                    default -> false;
                })
                .disabledPet(switch (((ToggleButton) disabledPetGroup.getSelectedToggle()).getText()) {
                    case "Yes" -> true;
                    default -> false;
                });
        if(petType == 0) {
            questionnaireResultBeanBuilder.programEducation(switch (((ToggleButton) programEducationGroup.getSelectedToggle()).getText()) {
                case "No" -> 0;
                default -> 1;
            });
            if(!btnSizeNotImportant.isSelected()) {
                questionnaireResultBeanBuilder.size(switch (((ToggleButton) petSizeGroup.getSelectedToggle()).getText()) {
                    case "Small" -> 0;
                    case "Medium" -> 1;
                    case "Large" -> 2;
                    case "Extra Large" -> 3;
                    default -> -1;
                });
            }
        }

        addInformation(questionnaireResultBeanBuilder);
        QuestionnaireResultBean questionnaireResultBean = questionnaireResultBeanBuilder.build();
        QuestionnaireResultController questionnaireResultController = new QuestionnaireResultController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnaireResultPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        GUIQuestionnaireResultController guiQuestionnaireResultController = fxmlLoader.getController();
        guiQuestionnaireResultController.setPreviousPage(currentPage);
        guiQuestionnaireResultController.setCurrentPage(root);
        guiQuestionnaireResultController.setData(questionnaireResultController.searchPets(questionnaireResultBean));
        stage.setScene(scene);
        }

        public void addInformation(QuestionnaireResultBeanBuilder questionnaireResultBeanBuilder) {
            if(!btnAgeNotImportant.isSelected()) {
                questionnaireResultBeanBuilder.age(switch (((ToggleButton) petAgeGroup.getSelectedToggle()).getText()) {
                    case "Puppy (0-12 months)" -> "puppy";
                    case "Young (13 months - 3 years)" -> "young";
                    case "Adult (4 years - 10 years)" -> "adult";
                    case "Senior (more than 11 years)" -> "senior";
                    default -> "";
                });
            }
            else {
                questionnaireResultBeanBuilder.age("");
            }
            if(btnHaveAPet.isSelected()) {
                questionnaireResultBeanBuilder.maleCat(btnMaleCat.isSelected())
                        .femaleCat(btnFemaleCat.isSelected())
                        .maleDog(btnMaleDog.isSelected())
                        .femaleDog(btnFemaleDog.isSelected());
            }
        questionnaireResultBeanBuilder.specificArea(switch (((ToggleButton) specificAreaGroup.getSelectedToggle()).getText()) {
                case "Yes" -> true;
                default -> false;
            });
            if(btnSpecificArea.isSelected()) {
                questionnaireResultBeanBuilder.city(cityTextField.getText());
            }
        }

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void setCurrentPage(Parent currentPage) {
        this.currentPage = currentPage;
    }
}
