package com.tech;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Существует справки прописки и выбытия по месту жительства.
 * Справка состоит из полей (documentId, personId, addressId, type).
 * Справка прописки по месту жительства имеет тип 3.
 * Справка выбытия с места жительства имеет тип 11.
 *
 * Справка выбытия отменяет справку прибытия, если у них personId и addressId совпадают друг с другом.
 * На вход метода передается список справок и список documentId для фильтрации выходных данных.
 *
 * Метод должен выдавать только активные места регистрации для переданных documentId.
 *
 * Пример передаваемых данных
 * Список справок:
 * (1, 1, 1, 3), (2, 1, 1, 11), (3, 1, 2, 3), (4, 2, 1, 3), (5, 3, 1, 3)
 * Список documentId: 1, 2, 3, 5
 * На выходе должны получить
 * (3, 1, 2, 3), (5, 3, 1, 3)
 */
public class TechnicalInterviewTask {

    private final static Long IN = 3L;
    private final static Long OUT = 11L;
    public static void main(String[] args) {

        // условия задачи (список справок)
        ArrayList<RegistrationAction> rList = new ArrayList<>(Arrays.asList(
                new RegistrationAction(1L, 1L, 1L, 3L, 0),
                new RegistrationAction(2L, 1L, 1L, 11L, 0),
                new RegistrationAction(3L, 1L, 2L, 3L, 0),
                new RegistrationAction(4L, 2L, 1L, 3L, 0),
                new RegistrationAction(5L, 3L, 1L, 3L, 0)
        ));

        // передаваемые данные по условию (список documentId)
        Long[] documentsIds = new Long[4];
        documentsIds[0] = 1L;
        documentsIds[1] = 2L;
        documentsIds[2] = 3L;
        documentsIds[3] = 5L;

        // решение с циклами
        var result1 = getResult(rList, documentsIds);
        System.out.println(result1);
        
        // решение через Stream API
        var result = getResultStreamApi(rList, documentsIds);
        System.out.println(result);
    }

    public static List<RegistrationAction> getResult(ArrayList<RegistrationAction> list, Long[] documentId) {

        HashMap<String, RegistrationAction> processedKeys = new HashMap<>();
        Set<Long> docIds = new HashSet<>(Arrays.asList(documentId));

        for (int i = 0; i < list.size(); i++) {

            Long curDocId = list.get(i).getDocumentId(); // documentId
            Long curPersonId = list.get(i).getPersonId(); // personId
            Long curAddressId = list.get(i).getAddressId(); // addressId
            Long curType = list.get(i).getType(); // type

            if (docIds.contains(curDocId)) { // тот документ, который нас интересует

                // составной уникальный ключ
                String key = "p" + curPersonId + "a" + curAddressId;

                if (processedKeys.containsKey(key)) {
                    var tmp = processedKeys.get(key);

                    var resT = tmp.getResult();
                    if (curType.equals(IN)) {
                        // справка прибытия увеличивает на 1 общий рейтинг
                        resT++;
                    } else if (curType.equals(OUT)) {
                        // справка выбытия уменьшает на 1 общий рейтинг действия
                        resT--;
                    }

                    RegistrationAction newObj = new RegistrationAction(
                            curDocId,
                            curPersonId,
                            curAddressId,
                            curType,
                            resT // в параметр result собираем данные
                    );

                    processedKeys.put(key, newObj);
                } else {
                    var resT = list.get(i).getResult();
                    if (curType.equals(IN)) {
                        resT++;
                    } else if (curType.equals(OUT)) {
                        resT--;
                    }
                    list.get(i).setResult(resT);

                    processedKeys.put(key, list.get(i));
                }
            }
        }

        var result = new ArrayList<RegistrationAction>();
        for (Map.Entry<String, RegistrationAction> proc: processedKeys.entrySet()) {
            if (proc.getValue().getResult() > 0) { 
                // оставляем только тех, кто в результате всех действий остался зарегистрирован
                result.add(proc.getValue());
            }
        }

        return result.stream().toList();
    }

    public static List<RegistrationAction> getResultStreamApi(ArrayList<RegistrationAction> list, Long[] documentId) {
        // множество id документов, которое нас интересует
        Set<Long> findedDocs = new HashSet<>(Arrays.asList(documentId));

        return list.stream()
                .filter( o -> findedDocs.contains(o.getDocumentId()))
                .map(entry -> {
                    String key = "p" + entry.getPersonId() + "a" + entry.getAddressId();
                    var resT = 0;
                    if (IN.equals(entry.getType())) {
                        resT++;
                    } else if (OUT.equals(entry.getType())) {
                        resT--;
                    }
                    var value = new RegistrationAction(
                            entry.getDocumentId(),
                            entry.getPersonId(),
                            entry.getAddressId(),
                            entry.getType(),
                            resT
                    );
                    return Map.entry(key, value);
                })
                .collect(Collectors.groupingBy(
                    Map.Entry::getKey,

                    Collectors.collectingAndThen(
                            Collectors.reducing((obj1, obj2) -> {
                                obj1.getValue().setResult(
                                        obj1.getValue().getResult() + obj2.getValue().getResult()
                                );
                                return obj1;
                            }),
                            Optional::get
                    ))
                )
                .entrySet()
                .stream()
                .filter(o -> o.getValue().getValue().getResult() > 0)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getValue()
                        )
                )
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    /**
     * Объект действия "Регистрация"
     */
    static class RegistrationAction {
        private final Long documentId;
        private final Long personId;
        private final Long addressId;
        private final Long type;

        /**
         * Результирующий рейтинг действия
         */
        private long result;

        public RegistrationAction(long documentId, long personId, long addressId, long type, long result) {
            this.documentId = documentId;
            this.personId = personId;
            this.addressId = addressId;
            this.type = type;
            this.result = result;
        }

        public Long getDocumentId() {
            return documentId;
        }

        public Long getPersonId() {
            return personId;
        }

        public Long getAddressId() {
            return addressId;
        }

        public Long getType() {
            return type;
        }

        public Long getResult() {
            return result;
        }

        public void setResult(long result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "{" + this.documentId + ", " + this.personId + ", " + this.addressId + ", " + this.type + "}";
        }
    }
}
