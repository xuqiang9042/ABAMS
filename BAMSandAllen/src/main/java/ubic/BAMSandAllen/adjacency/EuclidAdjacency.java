/*******************************************************************************
 * The ABAMS project
 * 
 * Copyright (c) 2012 University of British Columbia
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ubic.BAMSandAllen.adjacency;

import java.io.Serializable;

import javax.vecmath.Point3d;

import ubic.basecode.dataStructure.matrix.DenseDoubleMatrix;
import ubic.basecode.dataStructure.matrix.DoubleMatrix;
import ubic.basecode.math.CorrelationStats;

public class EuclidAdjacency extends AdjacencyComputeSlow implements AdjacencyCompute, Serializable {

    public EuclidAdjacency() {
        super();
    }

    public EuclidAdjacency( DoubleMatrix<String, String> matrix ) {
        super( matrix );
        // TODO Auto-generated constructor stub
    }

    public EuclidAdjacency clone() {
        return this;
    }

    public DoubleMatrix<String, String> getAdjacency( DoubleMatrix<String, String> input ) {
        int size = input.columns();
        DoubleMatrix<String, String> result = new DenseDoubleMatrix<String, String>( size, size );
        result.setColumnNames( input.getColNames() );
        result.setRowNames( input.getColNames() );
        for ( int i = 0; i < size; i++ ) {
            double[] iCol = input.getColumn( i );
            Point3d a = new Point3d( iCol );
            for ( int j = i; j < size; j++ ) {
                double[] jCol = input.getColumn( j );
                Point3d b = new Point3d( jCol );

                double value = a.distance( b );

                result.set( i, j, value );
                result.set( j, i, value );
            }
        }
        return result;
    }
}
