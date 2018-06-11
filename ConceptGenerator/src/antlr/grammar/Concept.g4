grammar Concept ;

import JavaLexerRules;

@parser::header {package antlr;}

compilationUnit
	: packageDeclaration? importDeclaration* conceptDeclaration* EOF
	;

packageDeclaration
	: 'package' packageName ';'
	;

packageName
	: Identifier
	| packageName '.' Identifier
	;

importDeclaration
	: 'import' 'static'? qualifiedName ('.' '*')? ';'
	;

conceptDeclaration
	: conceptModifier* 'concept' Identifier 'base'? superConcept? includeConcept?  conceptBody
	;

conceptModifier
	: 'public'
	| 'protected'
	| 'private'
	| 'static'
	| 'final'
	;

//relationalClausel
//	: superConcept
//	| includeConcept
//	;

superConcept
	: 'extends' referenceType
	;

includeConcept
	: 'includes' referenceType (',' referenceType) *
	;

conceptBody
	: '{' attribute* '}'
	;

attribute
	: simpleAttribute
	| complexAttribute
	;
	
simpleAttribute
	: dataAttribute
	| conceptAttribute
	;
	
complexAttribute
	: chainAttribute
	| functionAttribute
	| aggregatingAttribute
	;


dataAttribute
	: Identifier ':' LIST? primitiveType relation? propertyList? 
	;

conceptAttribute
	: Identifier ':' LIST? referenceType relation? propertyList? 
	;
	
simpleType
	: primitiveType
	| referenceType
	;

//toDo 		 
chainAttribute
	: conceptAttribute('concat' conceptAttribute)+ 'concat' dataAttribute
	;

functionAttribute
	: Identifier '=' 'func' simpleType '{' expr '}' 
	;

expr
	: (.)*?
	;

aggregatingAttribute
	: Identifier '=' simpleType '(' 'start' '=' expr ','  'ag' '=' expr ',' 'fin' '=' expr  ')'
	;

range
	: 'range' referenceType
	;

propertyList
	: '(' property (',' property)* ')'
	;

property
	: TRANSITIVE
	| REFLEXIVE
	;
	
relation
	: (SYMMETRIC|INVERS) Identifier
	;
	
LIST : 'list';
TRANSITIVE : 'transitive';
SYMMETRIC : 'symmetric';
REFLEXIVE : 'reflexive';
INVERS : 'inverse';

//toDo add referenceType

referenceType
	: Identifier
	;

primitiveType
	: 'byte'
	| 'short'
	| 'int'
	| 'long'
	| 'char'
	| 'boolean'
	| 'float'
	| 'double'
	| 'string'
	| 'date'
	| 'datetime'
	;

qualifiedName
	: Identifier ('.' Identifier)*
	;

