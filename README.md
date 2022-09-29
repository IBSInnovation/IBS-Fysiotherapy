# IBS-Fysiotherapy

[link naar front end code](https://github.com/hu-project334/project334/tree/main)

## Lombok
The used annotations from lombok are:
@Getter
@Setter
@ToString
@Builder
@Builder.Default

@Getter generates a getter for all attributes
@Setter generates a setter for all attributes
@ToString generates a toString with all attributes
@Builder allows you to use the builder class with the class
@Builder.Default makes sure a list is created when a new object of the class is initialized

If you are wondering why @Data is not used instead of all these lose tags, that is because the @Data tag also adds the @EqualsAndHashCode tag.
Since we are using Springboot we dont want this tag, because of the generated Hascode method. Normally a hashcode is generated by looking at the entire object when it is created.
In springboot you *need* an ID tag if you want to persist an object, and this id is only generated after you've persisted the object. This means that if you hash the object before the id is generated your hash will not be accurate.
This can cause unforseen bugs and errors, which is why the @Data tag is nog used.