var objectUtils = (function(){
    'use strict';

    var objectUtils = {
        isEmpty : function(object){
            return this.isUndefined(object)
                || this.isEmptyString(object)
                || this.isZero(object)
                || this.isFunction(object)
                || !this.hasElements(object);
        },
        isUndefined : function(object){
            return object == undefined;
        },
        isNumber : function(object) {
            if (this.isUndefined(object)){
                return false;
            }
            return !this.isObjectOrArray(object) && typeof object == 'number' && !isNaN(object);
        },
        isNumeric : function(object){
            if (this.isUndefined(object)){
                return false;
            }
            return !this.isObjectOrArray(object) && !isNaN(parseInt(object, 10));
        },
        isZero : function(object){
            return this.isNumber(object) && object == 0;
        },
        isEmptyString : function(object){
            return this.isString(object) && "" == object.trim();
        },
        isString : function(object){
            if (this.isUndefined(object)){
                return false;
            }
            return typeof object == "string";
        },
        isFunction : function(object){
            if (this.isUndefined(object)){
                return false;
            }
            return typeof object == "function";
        },
        isObjectOrArray : function(object){
            if (this.isUndefined(object)){
                return false;
            }
            return typeof object == 'object';
        },
        hasElements : function(object){
            if (this.isArray(object) || this.isObject(object) || this.isString(object)){
                object = this.isString(object) ? object.trim() : object;
                for(var idx in object){
                    return true;
                }
            }
            return false;
        },
        isArray : function(object){
            return this.isObjectOrArray(object) && JSON.stringify(object).indexOf('[') > -1;
        },
        isObject : function(object){
            return this.isObjectOrArray(object) && JSON.stringify(object).indexOf('{') > -1;
        }
    };

    if (typeof module !== 'undefined' && module.exports) {
        module.exports = objectUtils;
    } else {
        return objectUtils;
    }
})();