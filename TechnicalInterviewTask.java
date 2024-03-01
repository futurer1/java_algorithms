package com.tech;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Существует документ прописки и выписки по месту пребывания.
 * Документ состоит из полей (documentId, personId, addressId, type).
 * Документ прописки по месту пребывания имеет тип 6.
 * Документ выписки с места жительства имеет тип 13.
 *
 * Документ выписки отменяет документ прописки, если у них personId и addressId совпадают друг с другом.
 * На вход метода передается список документов и список documentId для фильтрации выходных данных.
 *
 * Метод должен выдавать только тех, кто остался зарегистрирован для переданных documentId.
 *
 * Пример передаваемых данных
 * Список справок:
 * (1, 1, 1, 3), (2, 1, 1, 11), (3, 1, 2, 3), (4, 2, 1, 3), (5, 3, 1, 3)
 * Список documentId: 1, 2, 3, 5
 * На выходе должны получить
 * (3, 1, 2, 3), (5, 3, 1, 3)
 */
public class TechnicalInterviewTask {

    private final static Long IN = 6L;
    private final static Long OUT = 13L;
    public static void main(String[] args) {

        // условия задачи
        ArrayList<RegistrationAction> rList = new ArrayList<>(Arrays.asList(
                new RegistrationAction(1L, 1L, 1L, 3L, 0),
                new RegistrationAction(2L, 1L, 1L, 11L, 0),
                new RegistrationAction(3L, 1L, 2L, 3L, 0),
                new RegistrationAction(4L, 2L, 1L, 3L, 0),
                new RegistrationAction(5L, 3L, 1L, 3L, 0)
        ));

        // передаваемые данные по условию
        Long[] documentsIds = new Long[4];
        documentsIds[0] = 1L;
        documentsIds[1] = 2L;
        documentsIds[2] = 3L;
        documentsIds[3] = 5L;

        // решение с циклами
        var result = getResult(rList, documentsIds);
        System.out.println(result);

        // решение через Stream API
        var result1 = getResultStreamApi(rList, documentsIds);
        System.out.println(result1);

        // оптимизированное решение через Stream API
        var result2 = getResultStreamApiOptimized(rList, documentsIds);
        System.out.println(result2);

        // Ещё более короткий вариант на стримах
        var result3 = getResultStreamApiOptimized(rList, documentsIds);
        System.out.println(result3);
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
                        // документ прописки увеличивает на 1 общий рейтинг
                        resT++;
                    } else if (curType.equals(OUT)) {
                        // документ выписки уменьшает на 1 общий рейтинг действия
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

    public static List<RegistrationAction> getResultStreamApiOptimized(ArrayList<RegistrationAction> list, Long[] documentId) {
        Set<Long> findedDocs = new HashSet<>(Arrays.asList(documentId));

        return list.stream()
                .filter(entry -> findedDocs.contains(entry.getDocumentId()))
                .collect(Collectors.groupingBy(
                        entry -> "p" + entry.getPersonId() + "a" + entry.getAddressId(),
                        Collectors.collectingAndThen(
                                Collectors.reducing((obj1, obj2) -> {
                                    var result = obj1.getResult();
                                    if (IN.equals(obj2.getType())) {
                                        result++;
                                    } else if (OUT.equals(obj2.getType())) {
                                        result--;
                                    }
                                    obj1.setResult(result);
                                    return obj1;
                                }),
                                Optional::get
                        )
                ))
                .values()
                .stream()
                .filter(entry -> entry.getResult() > 0)
                .collect(Collectors.toList());
    }

    public static List<RegistrationAction> getResultStreamApiOptimized1(ArrayList<RegistrationAction> list, Long[] documentId) {
        Set<Long> findedDocs = new HashSet<>(Arrays.asList(documentId));

        return list.stream()
                .filter(entry -> findedDocs.contains(entry.getDocumentId())) // фильтруем только те документы, которые нужны
                .collect(Collectors.toMap( // Создаем мапу, ключи которой строка с уникальным набором данных.
                        // ключ - признак отмены документа прописки документом выписки
                        entry -> "p" + entry.getPersonId() + "a" + entry.getAddressId(),
                        Function.identity(), // возвращает входные аргументы самой себя (маппер значений)
                        (obj1, obj2) -> { // функция, которая используется при мерже значений, если сопрадает ключ
                            var result = obj1.getResult();
                            if (IN.equals(obj2.getType())) { // прописка
                                result++;
                            } else if (OUT.equals(obj2.getType())) { // выписка
                                result--;
                            }
                            obj1.setResult(result);
                            return obj1;
                        }
                )) // Map<String, RegistrationAction>
                .values() // Collection<RegistrationAction> (уменьшили размерность с мапы до коллекции)
                .stream() // Stream<RegistrationAction>
                .filter(entry -> entry.getResult() > 0) // фильтруем только тех, кто остался прописан
                .collect(Collectors.toList());
    }

    /**
     * Объект "Регистрационное действие"
     */
    static class RegistrationAction {
        private final Long documentId;
        private final Long personId;
        private final Long addressId;
        private final Long type;

        /**
         * Результирующий рейтинг действия
         */
        private Long result;

        public RegistrationAction(Long documentId, Long personId, Long addressId, Long type, Long result) {
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

        public void setResult(Long result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "{" + this.documentId + ", " + this.personId + ", " + this.addressId + ", " + this.type + "}";
        }
    }
}
