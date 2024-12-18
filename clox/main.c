#include <stdio.h>
#include <string.h>

typedef struct Node Node;
typedef struct List List;

struct Node{
    Node* prev;
    Node* next;
    char* text;
};

struct List{
    int size;
    Node* head;
    Node* tail;
};

void insert(List *l, char azard[], Node* newNode){

    newNode->text = azard;
    if (l->size == 0){
        l->size += 1;
        l->head = newNode;
        l->tail = newNode;
        printf("%s\n", azard);
        return;
    } else{
        l->size += 1;
        l->tail->next = newNode;
        newNode->prev = l->tail;
        l->tail = newNode;
        printf("%s\n", azard);
        return;
    }
}

int find(List* l, char azard[]){

    Node currNode = *l->head;
    for (int i = 0; i < l->size; i++){
        printf("%d\n", i);
        if (strcmp(azard,currNode.text) == 0){
            return i;
        }
        currNode = *currNode.next;
    }
    return -1;
}

int delete(List* l, char azard[]){
    Node currNode = *l->head;
    for (int i = 0; i < l->size; i++){
        if (strcmp(azard,currNode.text) == 0){
            Node* prev = currNode.prev;
            Node* next = currNode.next;
            if (i>0){
                prev->next = next;
            } else {
                l->head = next;
            }
            if (i<l->size-1){
                next->prev = prev;
            } else {
                l->tail = prev;
            }
            l->size -= 1;

            return i;
        }
        currNode = *currNode.next;
    }
    return -1;
}

void printList(List l){
    Node currNode = *l.head;
    for (int i = 0; i < l.size; i++){
        printf("%s\n",currNode.text);
        currNode = *currNode.next;
    }
}

int main() {
    printf("Hello, World!\n");

    List l;
    l.size =0;
    Node newNode1;
    Node newNode2;
    Node newNode3;
    Node newNode4;
    insert(&l, "Hello", &newNode1);
    insert(&l, "World", &newNode2);
    insert(&l, "I am", &newNode3);
    insert(&l, "ready", &newNode4);
    printList(l);
    delete(&l, "Hello");
    delete(&l, "ready");
    printList(l);
    delete(&l, "World");
    delete(&l, "I am");
    insert(&l, "Hello", &newNode1);
    insert(&l, "World", &newNode2);
    insert(&l, "I am", &newNode3);
    insert(&l, "ready", &newNode4);
    printList(l);
    delete(&l, "World");
    printList(l);
}
