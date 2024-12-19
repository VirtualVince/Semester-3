namespace lab4;

// A sealed class can inherit from a parent class 
public class SealedInherits
{
}

// We use sealed classes when we don't want any other programmers to make changes/updates to our class
public sealed class SealedClass : SealedInherits
{
}

// Cannot inherit from a sealed class
/*
public class InheritFromSealed : SealedClass
{
}
*/